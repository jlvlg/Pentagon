/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Page;

/**
 * Thrown when trying to access a non-existing page
 * @author Lucas
 *
 */
public class PageNotFoundException extends ObjectNotFoundException {
	private Page page;
	
	public PageNotFoundException() {
		super("Page");
	}
	
	public PageNotFoundException(Page page) {
		this();
		this.page = page;
	}

	public Page getPage() {
		return page;
	}
}
