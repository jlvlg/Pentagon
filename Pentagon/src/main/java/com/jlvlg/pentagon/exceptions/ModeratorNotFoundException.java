package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when a page moderator search fails
 * @author Lucas
 *
 */
public class ModeratorNotFoundException extends ModeratorPageGenericException {
	private static final long serialVersionUID = 5734889831519271487L;

	public ModeratorNotFoundException(Page page, Moderator moderator) {
		super(page, moderator, "Requested moderator not found in page");
	}
}
