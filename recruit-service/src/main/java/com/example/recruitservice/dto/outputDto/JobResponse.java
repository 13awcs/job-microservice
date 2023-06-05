//package com.example.recruitservice.dto.outputDto;
//
//import com.example.Jobportal.model.Job;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Builder;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Data
//@Builder
//public class JobResponse {
//    private Long id;
//    private String title;
//    private int amount;
//    private String type;
//    private String level;
//    private LocalDate deadline;
//    private String description;
//    private String companyName;
//    private Float salary;
//    private String location;
//    private String recruiter;
//    @JsonFormat(pattern="dd-MM-yyyy")
//    private LocalDate createAt;
//
//    public static JobResponse fromEntity(Job job) {
//        return JobResponse.builder()
//                .id(job.getId())
//                .title(job.getTitle())
//                .amount(job.getAmount())
//                .type(job.getType())
//                .level(job.getLevel())
//                .deadline(job.getDeadline())
//                .description(job.getDescription())
//                .companyName(job.getCompanyName())
//                .salary(job.getSalary())
//                .location(job.getLocation())
//                .recruiter(job.getRecruiter().getName())
//                .createAt(job.getCreateAt())
//                .build();
//    }
//}
