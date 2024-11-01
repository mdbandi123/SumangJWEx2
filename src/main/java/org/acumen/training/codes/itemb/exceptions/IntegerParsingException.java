package org.acumen.training.codes.itemb.exceptions;

import jakarta.servlet.ServletException;

public class IntegerParsingException extends ServletException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public IntegerParsingException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public IntegerParsingException() {
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
