package com.example.recruitservice.repository;

import com.example.recruitservice.modal.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    Recruiter findByUsername(String username);
}
