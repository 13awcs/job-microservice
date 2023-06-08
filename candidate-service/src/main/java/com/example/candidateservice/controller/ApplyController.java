package com.example.candidateservice.controller;

import com.example.candidateservice.common.ResponseObject;
import com.example.candidateservice.modal.dto.ApplyOutputDto;
import com.example.candidateservice.modal.entity.Apply;
import com.example.candidateservice.repository.ApplyRepository;
import com.example.candidateservice.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apply")
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

    @GetMapping("/applies/{recruiterId}/search")
    public ResponseEntity<Page<ApplyOutputDto>> searchApplyByStatus(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                                                    @RequestParam(name = "sortField", defaultValue = "applyDate") String sortField,
                                                                    @RequestParam(name = "sortDir", required = false, defaultValue = "ASC") Sort.Direction sortDir,
                                                                    @RequestParam(value = "status") String status,
                                                                    @PathVariable Long recruiterId) {
        Pageable pageable = PageRequest.of(page, size, sortDir, sortField);
        return ResponseEntity.ok(applyService.searchApplyByStatus(status,recruiterId,pageable));
    }

    @GetMapping("/applies/has-status/{recruiterId}")
    public ResponseEntity<Page<Apply>> getApplyHasStatusByDate(@PathVariable Long recruiterId, @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                               @RequestParam(name = "size", defaultValue = "5") Integer size,
                                                               @RequestParam(name = "sortField", defaultValue = "APPLY_DATE") String sortField,
                                                               @RequestParam(name = "sortDir", defaultValue = "ASC") Sort.Direction sortDir) {
        Pageable pageable = PageRequest.of(page, size, sortDir, sortField);
        return ResponseEntity.ok(applyService.getApplyHasStatusByRecruiterIdAndSortByDate(recruiterId, pageable));
    }
}
