package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
