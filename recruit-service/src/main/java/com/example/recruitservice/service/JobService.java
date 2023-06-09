package com.example.recruitservice.service;

import com.example.recruitservice.common.exceptions.ResourceNotFoundException;
import com.example.recruitservice.dto.Mapping.JobMapping;
import com.example.recruitservice.dto.inputDto.JobEditDto;
import com.example.recruitservice.dto.outputDto.JobOutputDto;
import com.example.recruitservice.dto.response.TopJobResponse;
import com.example.recruitservice.modal.dto.ApplyForJob;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.job.JobRequest;
import com.example.recruitservice.modal.dto.job.JobResponse;
import com.example.recruitservice.modal.entity.Job;
import com.example.recruitservice.modal.entity.Recruiter;
import com.example.recruitservice.repository.JobRepository;
import com.example.recruitservice.repository.RecruiterRepository;
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
public class JobService {
    private final String APPLY_URL = "http://localhost:8000/candidate/apply";
    private final JobRepository jobRepository;
    private final RecruiterRepository recruiterRepository;

    @Transactional
    public ServerResponseDto save(JobRequest request) {
        Long id = request.getId();
        Job job = new Job();
        if (id == null) {
            job.setId(request.getId());
            job.setTitle(request.getTitle());
            job.setDescription(request.getDescription());
            job.setAmount(request.getAmount());
            job.setCompanyName(request.getCompanyName());
            job.setSalary(request.getSalary());
            job.setLocation(request.getLocation());
            job.setStatus(request.getStatus());
//            job.setCreateAt(new Date());
        } else {
            Optional<Job> jobOpt = jobRepository.findById(id);
            if (jobOpt.isEmpty()) {
                return ServerResponseDto.ERROR;
            }
            job = jobOpt.get();
            job.setTitle(request.getTitle());
            job.setDescription(request.getDescription());
            job.setAmount(request.getAmount());
            job.setCompanyName(request.getCompanyName());
            job.setSalary(request.getSalary());
            job.setLocation(request.getLocation());
            job.setStatus(request.getStatus());
        }
        jobRepository.save(job);
        return ServerResponseDto.SUCCESS;
    }

    @Transactional
    public Job editJob(Long id, JobEditDto jobEditDto) {
        Job jobFromDb = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));

        Job jobEdit = new Job();
        jobEdit.setId(id);
        jobEdit.setTitle(jobEditDto.getTitle());
        jobEdit.setAmount(jobFromDb.getAmount());
        jobEdit.setLevel(jobEditDto.getLevel());
        jobEdit.setDescription(jobFromDb.getDescription());
        jobEdit.setCompanyName(jobFromDb.getCompanyName());
        jobEdit.setSalary(jobEditDto.getSalary());
        jobEdit.setLocation(jobEditDto.getLocation());
        jobEdit.setStatus(jobFromDb.getStatus());
        jobEdit.setActive(jobFromDb.getActive());
        jobEdit.setCreateAt(jobFromDb.getCreateAt());
        jobEdit.setRecruiterId(jobFromDb.getRecruiterId());

        return jobRepository.save(jobEdit);
    }

    public String deleteJob(Long id) {

        Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));
        jobRepository.deleteById(id);
        return "Deleted successfully job id "+id;
    }

    public ServerResponseDto getJobByRecruiterId(Long recruiterId) {
        return ServerResponseDto.success(jobRepository.findByRecruiterId(recruiterId));
    }

    public List<JobOutputDto> sortJobByDate(Long id) {

        List<JobOutputDto> jobOutputDtos = new ArrayList<>();

        List<Job> jobs = jobRepository.sortJobsByDate(id);
        if(jobs.isEmpty()){
            new ResourceNotFoundException("No result !");
        }
        for(Job job : jobs){
            JobOutputDto jobOutputDto = JobMapping.jobInputToOutput(job);
            jobOutputDtos.add(jobOutputDto);
        }

        return jobOutputDtos;
    }

    public Job getDetailJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow();
        return job;
    }

    public List<Integer> countJobByMonthAndYear(int year){
        List<Job> list = jobRepository.getAllJobIsActive().stream()
                .filter(y -> y.getCreateAt().getYear() == year)
                .collect(Collectors.toList());
        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int month = i;
            Long count = list.stream()
                    .filter(m -> m.getCreateAt().getMonth().getValue() == month)
                    .count();
            countList.add(count.intValue());
        }
        return countList;
    }

    public JobResponse getJobByApplyId(Long applyId) {
        RestTemplate restTemplate = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl(APPLY_URL + "/get-by-id/" + applyId)
                .build()
                .toUri();
        ResponseEntity<ApplyForJob> response = restTemplate.exchange(url, HttpMethod.GET, null, ApplyForJob.class);
        ApplyForJob result = response.getBody();
        Optional<Job> jobOpt = jobRepository.findById(result.getJobId());

        return fromEntity(jobOpt.get());
    }

    public List<JobResponse> getJobByActiveIsFalse() {
        List<Job> jobs = jobRepository.getJobByActiveIsFalse();
        List<JobResponse> list = new ArrayList<>();
        Map<Long, String> mapRecruiter = new HashMap<>();
        List<Long> listRecruiterId = new ArrayList<>();
        if (jobs.isEmpty()) {
            new ResourceNotFoundException("No result !");
        }
        for (Job job : jobs) {
            listRecruiterId.add(job.getRecruiterId());
        }
        List<Recruiter> recruiters = recruiterRepository.findByIdIn(listRecruiterId);
        for (Recruiter recruiter : recruiters) {
            mapRecruiter.put(recruiter.getId(), recruiter.getName());
        }

        for (Job job : jobs) {
            JobResponse jobResponse = new JobResponse();
            jobResponse.setId(job.getId());
            jobResponse.setTitle(job.getTitle());
            jobResponse.setAmount(job.getAmount());
            jobResponse.setLevel(job.getLevel());
            jobResponse.setDescription(job.getDescription());
            jobResponse.setCompanyName(job.getCompanyName());
            jobResponse.setSalary(job.getSalary());
            jobResponse.setLocation(job.getLocation());
            jobResponse.setRecruiter(mapRecruiter.get(job.getRecruiterId()));
            jobResponse.setCreateAt(job.getCreateAt());
            list.add(jobResponse);
        }
        return list;
    }

    public JobResponse fromEntity(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .amount(job.getAmount())
                .level(job.getLevel())
                .description(job.getDescription())
                .companyName(job.getCompanyName())
                .salary(job.getSalary())
                .location(job.getLocation())
                .createAt(job.getCreateAt())
                .build();
    }

    @javax.transaction.Transactional
    public String setActive(Long id,String active){
        jobRepository.setActive(id,active);
        return "Set active successfully";
    }

    public Page<TopJobResponse> getTopJob(Pageable pageable) {
        return jobRepository.getTopJob(pageable);
    }


}
