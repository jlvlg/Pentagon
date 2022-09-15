package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Page;

public class InvalidPageNameException extends Exception {
	private static final long serialVersionUID = 4534772187391525369L;
	private Page page;
	
	public InvalidPageNameException(Page page) {
		super("A page name cannot be null or empty");
		this.page = page;
	}

	public Page getPage() {
		return page;
	}
}
