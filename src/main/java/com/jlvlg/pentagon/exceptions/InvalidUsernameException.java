/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to create a user with an invalid username
 * @author Lucas
 *
 */
public class InvalidUsernameException extends Exception {
	private final User user;
	
	public InvalidUsernameException(User user) {
		super("An username name cannot be null, empty, or contain spaces and/or special characters");
		this.user = user;
	}

	public User getUser() {
		return user;
	}	
}
