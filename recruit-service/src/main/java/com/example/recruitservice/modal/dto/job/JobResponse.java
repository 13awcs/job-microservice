package com.example.recruitservice.modal.dto.job;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Long id;
    private String title;
    private int amount;
    private String type;
    private String level;
    private LocalDate deadline;
    private String description;
    private String companyName;
    private Float salary;
    private String location;
    private String recruiter;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate createAt;


}
