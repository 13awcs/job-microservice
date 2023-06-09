package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByIdIn(Collection<Long> listId);
}
