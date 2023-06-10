package com.example.candidateservice.service;

import com.example.candidateservice.modal.dto.ApplyOutputDto;
import com.example.candidateservice.modal.dto.JobForApply;
import com.example.candidateservice.modal.entity.Apply;
import com.example.candidateservice.modal.entity.Candidate;
import com.example.candidateservice.repository.ApplyRepository;
import com.example.candidateservice.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final String RECRUIT_URL = "http://localhost:8000/recruit";
    private final ApplyRepository applyRepository;
    private final CandidateRepository candidateRepository;
    public List<Integer> countApplyByMonthAndYear(int year){
        List<Apply> list = applyRepository.findAll().stream()
                .filter(y -> y.getApplyDate().getYear() == year)
                .collect(Collectors.toList());
        List<Integer> listApply = new ArrayList<>();
        for (int i=1; i<=12;i++) {
            int month = i;
            Long count = list.stream()
                    .filter(m -> m.getApplyDate().getMonth().getValue() == month)
                    .count();
            listApply.add(count.intValue());
        }
        return listApply;
    }

    public Page<ApplyOutputDto> getApplyByTypeStatusByRecruiterIdAndSortByDate(Long id,Pageable pageable, String typeStatus) {
        JobForApply[] externalRes = getJobByRecruiterIdFromExternal(id);
        Map<Long, JobForApply> mapJob = new HashMap<>();
        Map<Long, Candidate> mapCandidate = new HashMap<>();
        List<Long> listJobId = new ArrayList<>();
        Page<Apply> applies;
        for (JobForApply jobForApply : externalRes) {
            listJobId.add(jobForApply.getId());
            mapJob.put(jobForApply.getId(), jobForApply);
        }

        List<Long> listCandidateId = applyRepository.findListCandidateIdByJobId(listJobId);
        List<Candidate> listCandidate = candidateRepository.findByIdIn(listCandidateId);
        for(Candidate candidate : listCandidate) {
            mapCandidate.put(candidate.getId(), candidate);
        }
        if (typeStatus.equals("1")) {
            applies = applyRepository.getApplyHasStatusByRecruiterIdAndSortByDate(listJobId,pageable);
        } else if (typeStatus.equals("2")) {
            applies = applyRepository.getApplyHasNoStatusByRecruiterIdAndSortByDate(listJobId,pageable);
        } else {
            System.err.println(typeStatus);
            applies = applyRepository.searchApplyByStatus(listJobId, pageable, typeStatus);
        }

        return applies.map(o -> {
            ApplyOutputDto dto = new ApplyOutputDto();
            dto.setId(o.getId());
            dto.setName(mapCandidate.get(o.getCandidateId()).getName());
            dto.setTitle(mapJob.get(o.getJobId()).getTitle());
            dto.setSalary(mapJob.get(o.getJobId()).getSalary());
            dto.setLocation(mapJob.get(o.getJobId()).getLocation());
            dto.setApplyDate(o.getApplyDate());
            dto.setStatus(o.getStatus());
            return dto;
        });
    }

    private JobForApply[] getJobByRecruiterIdFromExternal(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl(RECRUIT_URL + "/job/jobs/recruiter/" + id)
                .build()
                .toUri();
        ResponseEntity<JobForApply[]> response = restTemplate.exchange(url, HttpMethod.GET, null, JobForApply[].class);
        JobForApply[] result = response.getBody();
        return result;
    }

    @Transactional
    public String setStatus(Long id,String status) {
        applyRepository.setStatus(id,status);
        return "Set status successfully";
    }

    public Apply getByApplyId(Long applyId) {
        Optional<Apply> applyOpt = applyRepository.findById(applyId);
        return applyOpt.get();
    }

    public List<ApplyOutputDto> getNewestApply(Long id) {
        JobForApply[] externalRes = getJobByRecruiterIdFromExternal(id);
        Map<Long, JobForApply> mapJob = new HashMap<>();
        Map<Long, Candidate> mapCandidate = new HashMap<>();
        List<Long> listJobId = new ArrayList<>();
        List<ApplyOutputDto> result = new ArrayList<>();
        for (JobForApply jobForApply : externalRes) {
            listJobId.add(jobForApply.getId());
            mapJob.put(jobForApply.getId(), jobForApply);
        }

        List<Long> listCandidateId = applyRepository.findListCandidateIdByJobId(listJobId);
        List<Candidate> listCandidate = candidateRepository.findByIdIn(listCandidateId);
        for(Candidate candidate : listCandidate) {
            mapCandidate.put(candidate.getId(), candidate);
        }
        List<Apply> applies = applyRepository.getNewestApply(listJobId);
        for (Apply apply : applies) {
            ApplyOutputDto dto = new ApplyOutputDto();
            dto.setId(apply.getId());
            dto.setName(mapCandidate.get(apply.getCandidateId()).getName());
            dto.setTitle(mapJob.get(apply.getJobId()).getTitle());
            dto.setSalary(mapJob.get(apply.getJobId()).getSalary());
            dto.setLocation(mapJob.get(apply.getJobId()).getLocation());
            dto.setApplyDate(apply.getApplyDate());
            dto.setStatus(apply.getStatus());
            result.add(dto);
        }
        return result;
    }


}
