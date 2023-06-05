package com.example.recruitservice.dto.outputDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@Setter
@Getter
@ToString
public class JobOutputDto {
    private Long id;
    private String title;
    private String category;
    private int amount;
    private String type;
    private String level;
    private LocalDate deadline;
    private String description;
    private String companyName;
    private Float salary;
    private String location;
    private String status;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate createAt;
    private Integer apply;
}
