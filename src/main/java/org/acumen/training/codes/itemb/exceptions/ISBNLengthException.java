package org.acumen.training.codes.itemb.exceptions;

import jakarta.servlet.ServletException;

public class ISBNLengthException extends ServletException {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public ISBNLengthException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ISBNLengthException() {
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
