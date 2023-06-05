//package com.example.recruitservice.dto.outputDto;
//
//import com.example.Jobportal.model.Candidate;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//
//import java.util.Date;
//@Builder
//@Data
//@Setter
//@Getter
//@ToString
//public class CandidateOutputDto {
//    private String name;
//    private String education;
//
//    @JsonFormat(pattern="dd-MM-yyyy")
//    private Date dob;
//    private String phone;
//    private String email;
//    private String address;
//    private String level;
//    private String avatar;
//
//    public static CandidateOutputDto fromEntity(Candidate candidate) {
//        return CandidateOutputDto.builder()
//                .name(candidate.getName())
//                .education(candidate.getMajor())
//                .dob(candidate.getDob())
//                .phone(candidate.getPhone())
//                .email(candidate.getEmail())
//                .address(candidate.getAddress())
//                .level(candidate.getLevel())
//                .avatar(candidate.getAvatar())
//                .build();
//
//    }
//
//
//}
