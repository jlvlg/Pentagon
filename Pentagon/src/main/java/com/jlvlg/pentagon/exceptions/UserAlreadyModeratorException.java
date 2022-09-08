package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to add an already moderator user to a page's moderators list
 * @author Lucas
 *
 */
public class UserAlreadyModeratorException extends ModeratorPageGenericException {
	private static final long serialVersionUID = 3160537556545031094L;
	private User user;

	public UserAlreadyModeratorException(User user, Page page, Moderator moderator) {
		super(page, moderator, "The user is already a moderator");
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}
