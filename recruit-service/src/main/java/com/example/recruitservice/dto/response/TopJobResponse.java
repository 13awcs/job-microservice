package com.example.recruitservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public interface TopJobResponse {
     String getTitle();

     String getName();

     @JsonFormat(pattern="dd-MM-yyyy")
     LocalDate getCreatedAt();

     Long getAmount();
}
