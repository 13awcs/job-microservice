package com.example.candidateservice.controller;

import com.example.candidateservice.modal.dto.ServerResponseDto;
import com.example.candidateservice.modal.entity.Candidate;
import com.example.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @GetMapping("list")
    public ResponseEntity<List<Candidate>> getListCandidate() {
        return ResponseEntity.ok(candidateService.getListCandidate());
    }

    @GetMapping("profile/{id}")
    public ResponseEntity<ServerResponseDto> getProfileCandidate(@PathVariable Long id) {
        return ResponseEntity.ok(candidateService.getProfileCandidate(id));
    }
}
