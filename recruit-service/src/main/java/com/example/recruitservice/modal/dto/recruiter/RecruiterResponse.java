package com.example.recruitservice.modal.dto.recruiter;

import com.example.recruitservice.modal.entity.Recruiter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Builder
public class RecruiterResponse {
    private String name;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;
    private String address;
    private String companyName;
    private String email;
    private String phone;
    private String avatar;

    public static RecruiterResponse fromEntity(Recruiter recruiter) {
        return RecruiterResponse.builder()
                .name(recruiter.getName())
                .dob(recruiter.getDob())
                .address(recruiter.getAddress())
                .companyName(recruiter.getCompanyName())
                .email(recruiter.getEmail())
                .phone(recruiter.getPhone())
                .avatar(recruiter.getAvatar())
                .build();
    }
}
