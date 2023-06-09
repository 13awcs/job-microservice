package com.example.candidateservice.controller;

import com.example.candidateservice.common.ResponseObject;
import com.example.candidateservice.modal.dto.ServerResponseDto;
import com.example.candidateservice.modal.entity.Candidate;
import com.example.candidateservice.repository.CandidateRepository;
import com.example.candidateservice.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class CandidateController {
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public List<Candidate> getAllCandidate(){
        return candidateRepository.findAll();
    }

    @GetMapping("/candidate/{id}")
    public Optional<Candidate> getCandidateById(@PathVariable Long id){
        return candidateRepository.findById(id);
    }

    @GetMapping("/candidate-by-apply-id/{applyId}")
    public ResponseEntity<ResponseObject> getCandidateProfileByApplyId(@PathVariable Long applyId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(candidateService.getCandidateByApplyId(applyId)));
    }
}
