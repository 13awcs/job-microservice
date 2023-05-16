package com.example.recruitservice.service;

import com.example.recruitservice.modal.dto.LoginRequest;
import com.example.recruitservice.modal.dto.ResponseCase;
import com.example.recruitservice.modal.dto.ServerResponseDto;
import com.example.recruitservice.modal.dto.recruiter.RecruiterResponse;
import com.example.recruitservice.modal.entity.Recruiter;
import com.example.recruitservice.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final RecruiterRepository recruiterRepository;

    public ServerResponseDto getProfile(Long userId) {
        Optional<Recruiter> recruiterOpt = recruiterRepository.findById(userId);
        if (recruiterOpt.isEmpty()) {
            return ServerResponseDto.ERROR;
        }
        return ServerResponseDto.success(convertFromEntity(recruiterOpt.get()));
    }

    public ServerResponseDto login(LoginRequest request) {
        Recruiter recruiter = recruiterRepository.findByUsername(request.getUsername());
        if (recruiter == null) {
            return ServerResponseDto.ERROR;
        }
        if (request.getPassword().equals(recruiter.getPassword())) {
            return ServerResponseDto.SUCCESS;
        }
        return ServerResponseDto.with(ResponseCase.PASSWORD_NOT_RIGHT);
    }

    private RecruiterResponse convertFromEntity(Recruiter entity) {
        RecruiterResponse response = new RecruiterResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setAddress(entity.getAddress());
        response.setDob(entity.getDob());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        return response;
    }
}
