package org.acumen.training.codes.exceptions;

import jakarta.servlet.ServletException;

public class DecimalPrecisionException extends ServletException{
	private String errorMessage;
	
	public DecimalPrecisionException() {
	}
	
	public DecimalPrecisionException(String errorMessage) {
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
