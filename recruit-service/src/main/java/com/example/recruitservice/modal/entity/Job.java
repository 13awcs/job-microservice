package com.example.recruitservice.modal.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    private int amount;
    private String description;
    private String companyName;
    private Float salary;
    private String location;
    private String level;
    private String active;
    private String status;
    private Long recruiterId;
    private Integer apply;
    private LocalDate createAt;
}
