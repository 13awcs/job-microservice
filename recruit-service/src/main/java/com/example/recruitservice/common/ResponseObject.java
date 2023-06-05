package com.example.recruitservice.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResponseObject {
    private String message;
    private Object data;

    public ResponseObject(String message) {
        this.message = message;
    }

    public ResponseObject(Object data) {
        this.data = data;
    }

    public ResponseObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }

}
