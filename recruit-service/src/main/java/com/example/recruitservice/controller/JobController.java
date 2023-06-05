package com.example.recruitservice.controller;

import com.example.recruitservice.common.ResponseObject;
import com.example.recruitservice.dto.inputDto.JobEditDto;
import com.example.recruitservice.dto.outputDto.JobOutputDto;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.job.JobRequest;
import com.example.recruitservice.modal.entity.Job;
import com.example.recruitservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> save(@RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.save(request));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseObject> editJob(@PathVariable Long id,@RequestBody JobEditDto jobInputDto){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Edit job successfully !",jobService.editJob(id,jobInputDto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteJob(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(jobService.deleteJob(id)));
    }

    @GetMapping("/jobs/recruiter/{id}")
    public List<JobOutputDto> getAllJobsByRecruiterId(@PathVariable Long id){
        return jobService.sortJobByDate(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getDetailJob(id));
    }

    @GetMapping("/admin/statistic")
    public ResponseEntity<ResponseObject> countJobByMonth(@RequestParam int year){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success",jobService.countJobByMonthAndYear(year)));
    }

}
