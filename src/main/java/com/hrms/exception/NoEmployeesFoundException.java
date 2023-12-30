package com.hrms.exception;

@SuppressWarnings("serial")
public class NoEmployeesFoundException extends RuntimeException {

    public NoEmployeesFoundException(String message) {
        super(message);
    }
}
