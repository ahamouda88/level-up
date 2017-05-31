package com.levelup.persist.model.exception;

/**
 * An exception that is being thrown if a buddy/user has an account already
 * 
 * @author ahamouda
 *
 */
public class AccountAlreadyExitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountAlreadyExitsException(String message) {
		super(message);
	}
}
