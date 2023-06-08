package com.example.candidateservice.modal.dto;

import com.example.candidateservice.modal.entity.Apply;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Data
@Setter
@Getter
public class ApplyOutputDto {
    private Long id;
    private String name;
    private String title;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate applyDate;
    private String status;
    public static ApplyOutputDto fromEntity(Apply apply){
        return ApplyOutputDto.builder()
                .id(apply.getId())
//                .name(apply.getCandidateApply().getName())
//                .title(apply.getJobApply().getTitle())
                .applyDate(apply.getApplyDate())
                .status(apply.getStatus())
                .build();
    }
}
