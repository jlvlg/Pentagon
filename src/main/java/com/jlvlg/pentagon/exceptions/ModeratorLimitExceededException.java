package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to add a moderator past a page's moderators limit
 * @author Lucas
 *
 */
public class ModeratorLimitExceededException extends ModeratorPageGenericException {
	private static final long serialVersionUID = -5729026651583573179L;

	public ModeratorLimitExceededException(Page page, Moderator moderator) {
		super(page, moderator, "You cannot have more than 5 moderatos on a page");
	}
}
