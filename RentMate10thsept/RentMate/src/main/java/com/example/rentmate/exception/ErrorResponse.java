package com.example.rentmate.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private long timestamp;
    private String detail;

}
