package com.example.candidateservice.common.exceptions;


public class MethodArgumentTypeMismatchException extends RuntimeException{
    public MethodArgumentTypeMismatchException(String message){
        super(message);
    }
}

