package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.User;

/**
 * Thrown when trying to authenticate an user as a page's moderator
 * @author Lucas
 *
 */
public class UserIsNotModeratorException extends Exception {
	private static final long serialVersionUID = -8465838534363413957L;
	private Page page;
	private User user;

	public UserIsNotModeratorException(User user, Page page) {
		super("User is not a moderator of the page");
		this.page = page;
		this.user = user;
	}

	public Page getPage() {
		return page;
	}
	
	public User getUser() {
		return user;
	}
}
