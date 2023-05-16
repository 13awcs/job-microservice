package com.example.recruitservice.modal.dto.job;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class JobRequest {
    private Long id;

    private String title;
    private int amount;
    private String description;
    private String companyName;
    private Float salary;
    private String location;
    private String status;
    private Date createTime;
}
