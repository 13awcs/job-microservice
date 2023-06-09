package com.example.recruitservice.modal.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplyForJob {
    private Long id;

    private Long jobId;
    private Long candidateId;
    private LocalDate applyDate;
    private String status;
}
