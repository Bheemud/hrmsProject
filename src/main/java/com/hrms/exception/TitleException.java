package com.hrms.exception;


@SuppressWarnings("serial")
public class TitleException extends RuntimeException {

	public TitleException(String message) {
        super(message);
    }
 
    public TitleException(String message, Throwable cause) {
    	super(message, cause);
    }
}
