package com.example.candidateservice.modal.dto;

import lombok.Getter;

@Getter
public class ServerResponseDto {

    public static final ServerResponseDto SUCCESS = new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.SUCCESS);
    public static final ServerResponseDto ERROR = new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.ERROR);
    public static final ServerResponseDto UNAUTHENTICATED = new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.ERROR, "UNAUTHENTICATED");
    public static final ServerResponseDto IN_USE = new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.IN_USE);

    private final com.example.candidateservice.modal.dto.ResponseCase status;
    private Object data;

    private ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase responseCase) {
        this.status = responseCase;
    }

    private ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase responseCase, Object data) {
        this.status = responseCase;
        this.data = data;
    }

    public static ServerResponseDto success(Object data) {
        return new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.SUCCESS, data);
    }

    public static ServerResponseDto error(Object data) {
        return new ServerResponseDto(com.example.candidateservice.modal.dto.ResponseCase.ERROR, data);
    }

    public static ServerResponseDto with(com.example.candidateservice.modal.dto.ResponseCase responseCase) {
        return new ServerResponseDto(responseCase);
    }

    public static ServerResponseDto with(com.example.candidateservice.modal.dto.ResponseCase responseCase, Object data) {
        return new ServerResponseDto(responseCase, data);
    }
}
