package com.hrms.exception;

@SuppressWarnings("serial")
public class SalaryServiceException extends RuntimeException {

    public SalaryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

