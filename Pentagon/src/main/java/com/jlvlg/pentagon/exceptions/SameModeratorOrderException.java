package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to add a moderator to a page that already has one with the
 * same priority order
 * @author Lucas
 *
 */
public class SameModeratorOrderException extends InvalidModeratorOrderException {
	private static final long serialVersionUID = -3089296981761713761L;
	private Page page;
	
	public SameModeratorOrderException(Page page, Moderator moderator) {
		super(moderator, "A page cannot have two moderators with the same priority order");
		this.page = page;
	}

	public Page getPage() {
		return page;
	}
}
