package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Page;

public class PageNameTakenException extends Exception {
	private static final long serialVersionUID = -8111745786107516800L;
	private Page offendingPage;
	private Page storedPage;
	
	public PageNameTakenException(Page offendingPage, Page storedPage) {
		super("Two pages cannot have the same name");
		this.offendingPage = offendingPage;
		this.storedPage = storedPage;
	}

	public Page getOffendingPage() {
		return offendingPage;
	}

	public Page getStoredPage() {
		return storedPage;
	}
}
