package com.example.recruitservice.modal.dto.recruiter;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RecruiterResponse {
    private Long id;

    private String name;
    private Date dob;
    private String address;
    private String phone;
    private String email;
}
