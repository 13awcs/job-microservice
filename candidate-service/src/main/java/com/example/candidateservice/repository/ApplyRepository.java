package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
