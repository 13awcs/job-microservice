package com.example.recruitservice.repository;

import com.example.recruitservice.dto.response.TopRecruiterResponse;
import com.example.recruitservice.modal.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    @Query(value = "select * from recruiter r where r.username = :username", nativeQuery = true)
    Recruiter findByUsername(@Param("username") String username);

    List<Recruiter> findByIdIn(Collection<Long> listId);

    @Query(value = "SELECT R.NAME, R.DOB, R.COMPANY_NAME AS COMPANY, COUNT(J.ID) AS AMOUNT " +
            "FROM RECRUITER R JOIN JOB J " +
            "ON R.ID = J.RECRUITER_ID " +
            "GROUP BY R.ID " +
            "ORDER BY AMOUNT DESC ",nativeQuery = true)
    List<TopRecruiterResponse> getTopRecruiter();
}
