package com.example.recruitservice.service;

import com.example.recruitservice.dto.inputDto.RecruiterInput;
import com.example.recruitservice.dto.response.TopRecruiterResponse;
import com.example.recruitservice.modal.dto.LoginRequest;
import com.example.recruitservice.modal.dto.ResponseCase;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.recruiter.RecruiterResponse;
import com.example.recruitservice.modal.entity.Job;
import com.example.recruitservice.modal.entity.Recruiter;
import com.example.recruitservice.repository.JobRepository;
import com.example.recruitservice.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final JobRepository jobRepository;

    public Recruiter loadRecruiterByUsername(String username) {
        Recruiter recruiter = recruiterRepository.findByUsername(username);
        return recruiter;
    }

    public Recruiter editProfile(Long id, RecruiterInput recruiterInput) {
        Optional<Recruiter> recruiterOpt = recruiterRepository.findById(id);
        Recruiter recruiter = new Recruiter();
        recruiter = recruiterOpt.get();
        recruiter.setEmail(recruiterInput.getEmail());
        recruiter.setPhone(recruiterInput.getPhone());
        recruiter.setCompanyName(recruiterInput.getCompanyName());
        recruiter.setId(id);
        return recruiterRepository.save(recruiter);
    }

    public RecruiterResponse getRecruiterByJobId(Long jobId){
        Job job = jobRepository.findById(jobId).orElseThrow();
        Recruiter recruiter = recruiterRepository.findById(job.getRecruiterId()).orElseThrow();
        return RecruiterResponse.fromEntity(recruiter);
    }

    public List<TopRecruiterResponse> getTopRecruiter() {
        return recruiterRepository.getTopRecruiter();
    }
}
