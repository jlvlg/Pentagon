/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to access a non-existing user
 * @author Lucas
 *
 */
public class UserNotFoundException extends ObjectNotFoundException {
	private static final long serialVersionUID = 6421912423635500812L;
	private User user;
	
	public UserNotFoundException() {
		super("User");
	}

	public UserNotFoundException(User user) {
		this();
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
