package com.hrms.exception;

public class EmployeeDataNotFoundException extends RuntimeException {
	
	public EmployeeDataNotFoundException(String message) {
		super(message);
	}
}
