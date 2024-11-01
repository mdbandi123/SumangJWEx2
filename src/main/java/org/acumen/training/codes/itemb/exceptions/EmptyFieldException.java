package org.acumen.training.codes.itemb.exceptions;

import jakarta.servlet.ServletException;

public class EmptyFieldException extends ServletException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public EmptyFieldException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public EmptyFieldException() {
	}
	
	@Override
	public String getMessage() {
		return this.errorMessage;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(this.errorMessage);
	}
}
