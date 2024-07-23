package com.vivek.user.service.user.service.exceptions;

public class ResoueceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResoueceNotFoundException() {
		super("Resource not found on server !!");
	}
	
	public ResoueceNotFoundException(String message) {
		super(message);
	}
}
