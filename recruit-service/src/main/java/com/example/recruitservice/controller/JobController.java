package com.example.recruitservice.controller;

import com.example.recruitservice.common.ResponseObject;
import com.example.recruitservice.dto.inputDto.JobEditDto;
import com.example.recruitservice.dto.outputDto.JobOutputDto;
import com.example.recruitservice.dto.response.TopJobResponse;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.job.JobRequest;
import com.example.recruitservice.modal.entity.Job;
import com.example.recruitservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/jobs/job-by-apply-id/{applyId}")
    public ResponseEntity<ResponseObject> getJobByApplyId(@PathVariable Long applyId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(jobService.getJobByApplyId(applyId)));
    }

    @GetMapping("/admin/jobs-not-active")
    public ResponseEntity<ResponseObject> getJobByActiveIsFalse(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(jobService.getJobByActiveIsFalse()));
    }

    @PostMapping("/admin/set-active/{id}")
    public ResponseEntity<ResponseObject> setActive(@PathVariable Long id, @RequestParam String active){
        System.err.println("vao day");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(jobService.setActive(id,active)));
    }

    @GetMapping("/admin/statistic/top-job")
    public ResponseEntity<Page<TopJobResponse>> getTopJob(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "amount"));
        return ResponseEntity.ok(jobService.getTopJob(pageable));
    }

}
