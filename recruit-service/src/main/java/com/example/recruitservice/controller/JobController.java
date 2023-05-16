package com.example.recruitservice.controller;

import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.job.JobRequest;
import com.example.recruitservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recruit/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> save(@RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.save(request));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ServerResponseDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(jobService.delete(id));
    }

    @GetMapping("list/{recruiterId}")
    public ResponseEntity<ServerResponseDto> getJobByRecruiterId(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(jobService.getJobByRecruiterId(recruiterId));
    }

}
