package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to add a page with a name already in use
 * @author Lucas
 *
 */
public class PageNameTakenException extends NameTakenException {
	private static final long serialVersionUID = -8111745786107516800L;
	private Page offendingPage;
	private Page existingPage;
	
	public PageNameTakenException(Page offendingPage, Page existingPage) {
		super("page");
		this.offendingPage = offendingPage;
		this.existingPage = existingPage;
	}

	public Page getOffendingPage() {
		return offendingPage;
	}

	public Page getStoredPage() {
		return existingPage;
	}
}
