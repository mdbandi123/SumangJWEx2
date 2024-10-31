package org.acumen.training.codes.itema.exceptions;

import jakarta.servlet.ServletException;

public class LimitException extends ServletException{
	private String errorMessage;
	
	public LimitException() {
	}
	
	public LimitException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private static final long serialVersionUID = 1L;
	
	@Override
	public void printStackTrace() {
		System.err.println(this.errorMessage);
	}
	@Override
	public String getMessage() {
		return this.errorMessage;
	}
}
