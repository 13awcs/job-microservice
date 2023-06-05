package com.example.recruitservice.repository;

import com.example.recruitservice.modal.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    @Query(value = "select * from recruiter r where r.username = :username", nativeQuery = true)
    Recruiter findByUsername(@Param("username") String username);
}
