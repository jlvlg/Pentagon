package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to remove the only moderator of a page
 * @author Lucas
 *
 */
public class ZeroModeratorsException extends Exception {
	private static final long serialVersionUID = 5598781374793487183L;
	private Page page;
	private Moderator moderator;
	
	public ZeroModeratorsException(Page page, Moderator moderator) {
		super("There needs to be at least one moderator per page at all times");
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
