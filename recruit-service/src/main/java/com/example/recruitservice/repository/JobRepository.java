package com.example.recruitservice.repository;

import com.example.recruitservice.dto.response.TopJobResponse;
import com.example.recruitservice.modal.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "select j from Job j where j.active = '' order by j.createAt desc ")
    List<Job> getJobByActiveIsFalse();

    @Modifying
    @Query(value = "update job j set j.active = :active where j.id = :id",nativeQuery = true)
    void setActive(Long id,String active);

    @Query(value = "select j.title as title, " +
            "j.create_at as createAt, " +
            "r.name as name, " +
            "j.apply as amount " +
            "from job j " +
            "join recruiter r on j.recruiter_id = r.id ",
            nativeQuery = true)
    Page<TopJobResponse> getTopJob(Pageable pageable);

}
