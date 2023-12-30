package com.hrms.exception;

@SuppressWarnings("serial")
public class DepartmentCreationException extends RuntimeException {
	public DepartmentCreationException(String message) {
        super(message);
    }

}