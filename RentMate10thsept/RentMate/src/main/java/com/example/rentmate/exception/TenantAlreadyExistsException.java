package com.example.rentmate.exception;

public class TenantAlreadyExistsException extends RuntimeException {
    public TenantAlreadyExistsException(String s) {

        super(s);
    }
}
