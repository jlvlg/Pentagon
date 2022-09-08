package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to add a moderator out of order to a page
 * @author Lucas
 *
 */
public class NonSequentialModeratorOrderException extends InvalidModeratorOrderException {
	private static final long serialVersionUID = -3901103991643664728L;
	private Page page;
	
	public NonSequentialModeratorOrderException(Page page, Moderator moderator) {
		super(moderator, "Moderator order has to be sequential, you cannot skip a number");
		this.page = page;
	}

	public Page getPage() {
		return page;
	}
}
