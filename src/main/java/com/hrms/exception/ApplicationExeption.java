package com.hrms.exception;

@SuppressWarnings("serial")
public class ApplicationExeption extends RuntimeException {
	public ApplicationExeption() {
	}

	public ApplicationExeption(String msg) {
		super(msg);
	}
}
