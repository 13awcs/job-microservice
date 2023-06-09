package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query(value = "SELECT A.ID,A.CANDIDATE_ID,A.JOB_ID,A.APPLY_DATE,A.STATUS " +
            "FROM APPLY A " +
            "WHERE A.JOB_ID IN :listJobId AND A.STATUS like concat('%',:status,'%') ",
            countQuery = "Select count(A.ID) " +
                    "FROM APPLY A JOIN JOB J ON A.JOB_ID = J.ID " +
                    "WHERE A.JOB_ID IN :listJobId AND A.STATUS like concat('%',:status,'%') ",
            nativeQuery = true)
    Page<Apply> searchApplyByStatus(List<Long> listJobId, Pageable pageable, String status);

    @Query(value = "SELECT A.ID,A.CANDIDATE_ID,A.JOB_ID,A.APPLY_DATE,A.STATUS " +
            "FROM APPLY A " +
            "WHERE A.JOB_ID IN :listJobId AND A.STATUS != ''",
            countQuery = "Select count(A.ID) " +
                    "FROM APPLY A JOIN JOB J ON A.JOB_ID = J.ID " +
                    "WHERE A.JOB_ID IN :listJobId AND A.STATUS != '' ",
            nativeQuery = true)
    Page<Apply> getApplyHasStatusByRecruiterIdAndSortByDate(List<Long> listJobId, Pageable pageable);

    @Query(value = "SELECT A.ID,A.CANDIDATE_ID,A.JOB_ID,A.APPLY_DATE,A.STATUS " +
            "FROM APPLY A " +
            "WHERE A.JOB_ID IN :listJobId AND A.STATUS = ''",
            countQuery = "Select count(A.ID) " +
                    "FROM APPLY A JOIN JOB J ON A.JOB_ID = J.ID " +
                    "WHERE A.JOB_ID IN :listJobId AND A.STATUS = '' ",
            nativeQuery = true)
    Page<Apply> getApplyHasNoStatusByRecruiterIdAndSortByDate(List<Long> listJobId, Pageable pageable);

    @Query(value = "select a.candidate_id from apply a where a.job_id in :listJobId ", nativeQuery = true)
    List<Long> findListCandidateIdByJobId(List<Long> listJobId);

    @Modifying
    @Query(value = "update Apply p set p.status = :status where p.id = :id")
    void setStatus(Long id, String status);
}
