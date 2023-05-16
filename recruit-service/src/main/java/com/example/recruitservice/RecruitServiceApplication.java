package com.example.recruitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RecruitServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitServiceApplication.class, args);
    }

}
