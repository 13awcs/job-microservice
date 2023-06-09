package com.example.candidateservice.service;

import com.example.candidateservice.modal.dto.CandidateOutputDto;
import com.example.candidateservice.modal.dto.ServerResponseDto;
import com.example.candidateservice.modal.entity.Apply;
import com.example.candidateservice.modal.entity.Candidate;
import com.example.candidateservice.repository.ApplyRepository;
import com.example.candidateservice.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final ApplyRepository applyRepository;

    public List<Candidate> getListCandidate(){
        return candidateRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public ServerResponseDto getProfileCandidate(Long id) {
        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        return ServerResponseDto.success(candidateOpt.get());
    }

    public CandidateOutputDto getCandidateByApplyId(Long applyId) {
        Apply apply = applyRepository.findById(applyId).orElseThrow();
        Optional<Candidate> candidate = candidateRepository.findById(apply.getCandidateId());
        return CandidateOutputDto.fromEntity(candidate.get());
    }


}
