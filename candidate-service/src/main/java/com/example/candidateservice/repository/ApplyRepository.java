package com.example.candidateservice.repository;

import com.example.candidateservice.modal.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query(value = "select * from apply p where p.status != '' order by p.apply_date DESC ", nativeQuery = true)
    List<Apply> getNewestApply();
}
