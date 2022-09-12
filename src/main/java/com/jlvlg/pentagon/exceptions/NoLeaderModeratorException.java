package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to remove a page's leader moderator without demoting them first
 * @author lucas
 *
 */
public class NoLeaderModeratorException extends ModeratorPageGenericException {
	private static final long serialVersionUID = 1778315305378860267L;

	public NoLeaderModeratorException(Page page, Moderator moderator) {
		super(page, moderator, "The leader moderator of a page cannot be removed");
	}
}
