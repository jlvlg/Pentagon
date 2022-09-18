/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.User;

/**
 * @author Lucas
 *
 */
public class UsernameTakenException extends Exception {
	private static final long serialVersionUID = 4112232291669422376L;
	private User offendingUser;
	private User existingUser;
	
	public UsernameTakenException(User offendingUser, User existingUser) {
		super("Two users cannot have the same username");
		this.offendingUser = offendingUser;
		this.existingUser = existingUser;
	}

	public User getOffendingUser() {
		return offendingUser;
	}

	public User getExistingUser() {
		return existingUser;
	}
}
