package com.example.recruitservice.service;

import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.job.JobRequest;
import com.example.recruitservice.modal.entity.Job;
import com.example.recruitservice.repository.JobRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

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
            job.setCreateTime(new Date());
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

    public ServerResponseDto delete(Long id) {
        jobRepository.deleteById(id);
        return ServerResponseDto.SUCCESS;
    }

    public ServerResponseDto getJobByRecruiterId(Long recruiterId) {
        return ServerResponseDto.success(jobRepository.findByRecruiterId(recruiterId));
    }


}
