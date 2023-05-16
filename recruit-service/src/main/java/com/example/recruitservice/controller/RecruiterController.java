package com.example.recruitservice.controller;

import com.example.recruitservice.modal.dto.LoginRequest;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recruit/recruiter")
@RequiredArgsConstructor
public class RecruiterController {
    private final RecruiterService recruiterService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ServerResponseDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(recruiterService.getProfile(userId));
    }

    @PostMapping("login")
    public ResponseEntity<ServerResponseDto> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(recruiterService.login(request));
    }

}
