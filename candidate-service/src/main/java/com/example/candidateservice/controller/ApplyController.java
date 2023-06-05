package com.example.candidateservice.controller;

import com.example.candidateservice.common.ResponseObject;
import com.example.candidateservice.repository.ApplyRepository;
import com.example.candidateservice.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apply")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyRepository applyRepository;

    private final ApplyService applyService;

    @GetMapping("/admin/statistic")
    public ResponseEntity<ResponseObject> countApplyByMonth(@RequestParam int year) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success", applyService.countApplyByMonthAndYear(year)));
    }

    @GetMapping("/applies/newest")
    public ResponseEntity<ResponseObject> getNewestApply() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(applyRepository.getNewestApply()));
    }
}
