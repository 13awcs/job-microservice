package com.example.recruitservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public interface TopRecruiterResponse {
    String getName();

    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate getDob();

     String getCompany();

    Long getAmount();
}
