package com.example.candidateservice.modal.dto;

import com.example.candidateservice.modal.entity.Candidate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Setter
@Getter
@ToString
public class CandidateOutputDto {
    private String name;
    private String education;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;
    private String phone;
    private String email;
    private String address;
    private String level;
    private String avatar;

    public static CandidateOutputDto fromEntity(Candidate candidate) {
        return CandidateOutputDto.builder()
                .name(candidate.getName())
                .dob(candidate.getDob())
                .phone(candidate.getPhone())
                .email(candidate.getEmail())
                .address(candidate.getAddress())
                .avatar(candidate.getAvatar())
                .build();

    }


}
