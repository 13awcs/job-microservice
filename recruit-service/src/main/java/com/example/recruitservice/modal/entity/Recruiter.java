package com.example.recruitservice.modal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "recruiter")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private Date dob;
    private String avatar;
    private String companyName;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String disable = "false";
}
