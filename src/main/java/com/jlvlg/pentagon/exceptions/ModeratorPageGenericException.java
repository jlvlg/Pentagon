package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Abstract exception class. Used by exceptions that relate to a page and a moderator
 * (except NonSequentialModeratorOrderException and SameModeratorOrderException as they
 * inherit from InvalidModeratorOrderException)
 * @author Lucas
 *
 */
public abstract class ModeratorPageGenericException extends Exception {
	private static final long serialVersionUID = 3156806354713824397L;
	private Page page;
	private Moderator moderator;
	
	public ModeratorPageGenericException(Page page, Moderator moderator, String reason) {
		super(reason);
		this.page = page;
		this.moderator = moderator;
	}

	public Page getPage() {
		return page;
	}

	public Moderator getModerator() {
		return moderator;
	}
}
