package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Apply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query(value = "select * from apply p where p.status != '' order by p.apply_date DESC ", nativeQuery = true)
    List<Apply> getNewestApply();

    @Query(value = "select p from Apply p " +
            "where p.status like concat('%',?1,'%') ")
    Page<Apply> searchApplyByStatus(String status, Pageable pageable);

    @Query(value = "SELECT A.ID,A.CANDIDATE_ID,A.JOB_ID,A.APPLY_DATE,A.STATUS " +
            "FROM APPLY A " +
            "WHERE A.JOB_ID IN :listJobId AND A.STATUS != ''",
            countQuery = "Select count(A.ID) " +
                    "FROM APPLY A JOIN JOB J ON A.JOB_ID = J.ID " +
                    "WHERE A.JOB_ID IN :listJobId AND A.STATUS != '' ",
            nativeQuery = true)
    Page<Apply> getApplyHasStatusByRecruiterIdAndSortByDate(List<Long> listJobId, Pageable pageable);
}
