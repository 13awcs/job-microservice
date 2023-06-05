package com.example.recruitservice.repository;

import com.example.recruitservice.modal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByRecruiterId(Long recruiterId);

    @Query(value = "select * from job j where j.recruiter_id = :id and j.active = 'true' order by j.create_at desc ",nativeQuery = true)
    List<Job> sortJobsByDate(Long id);

    @Query(value = "select count(j.apply) from job j where j.id = ?1", nativeQuery = true)
    Integer countApplyByJobId(Long jobId);

    @Query(value = "select * from job j where j.active = 'true'",nativeQuery = true)
    List<Job> getAllJobIsActive();
}
