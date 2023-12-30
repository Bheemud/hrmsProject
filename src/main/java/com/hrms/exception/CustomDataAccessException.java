package com.hrms.exception;

@SuppressWarnings("serial")
public class CustomDataAccessException extends RuntimeException {

    public CustomDataAccessException(String message) {
        super(message);
    }

    public CustomDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}