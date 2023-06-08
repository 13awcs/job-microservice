package com.example.candidateservice.service;

import com.example.candidateservice.modal.dto.ApplyOutputDto;
import com.example.candidateservice.modal.dto.JobForApply;
import com.example.candidateservice.modal.entity.Apply;
import com.example.candidateservice.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final String RECRUIT_URL = "http://localhost:8000/recruit";
    private final ApplyRepository applyRepository;
    public List<Integer> countApplyByMonthAndYear(int year){
        System.err.println(year);
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

    public Page<ApplyOutputDto> searchApplyByStatus(String status, Long id,  Pageable pageable) {
        JobForApply[] externalRes = getJobByRecruiterIdFromExternal(id);
        List<Long> listJobId = new ArrayList<>();
        for (JobForApply jobForApply : externalRes) {
            listJobId.add(jobForApply.getId());
        }
        System.err.println(listJobId);
        Page<Apply> applyPage =  applyRepository.searchApplyByStatus(status, pageable);

        return applyRepository.searchApplyByStatus(status, pageable).map(ApplyOutputDto::fromEntity);
    }

    public Page<Apply> getApplyHasStatusByRecruiterIdAndSortByDate(Long id,Pageable pageable) {
        JobForApply[] externalRes = getJobByRecruiterIdFromExternal(id);
        List<Long> listJobId = new ArrayList<>();
        for (JobForApply jobForApply : externalRes) {
            listJobId.add(jobForApply.getId());
        }
//        Page<ApplyOutputDto> applies = applyRepository.getApplyHasStatusByRecruiterIdAndSortByDate(listJobId,pageable).map(ApplyOutputDto::fromEntity);
        Page<Apply> applies = applyRepository.getApplyHasStatusByRecruiterIdAndSortByDate(listJobId,pageable);
        return applies;
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
}
