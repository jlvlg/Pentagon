package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to add a leader moderator or promote a non-leader moderator when there's already one present 
 * @author Lucas
 *
 */
public class LeaderModeratorPresentException extends ModeratorPageGenericException {
	private static final long serialVersionUID = 2763921153571872071L;

	public LeaderModeratorPresentException(Page page, Moderator moderator) {
		super(page, moderator, "There is already a leader moderator present on the page");
	}
}
