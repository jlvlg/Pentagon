package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNameTakenException;
import com.jlvlg.pentagon.models.Page;

public interface PageServiceInterface extends GenericServiceInterface<Page, Long>{
	/**
	 * Saves a page to the database
	 * @throws PageNameTakenException Two pages cannot have the same name
	 * @throws InvalidPageNameException
	 */
	@Override
	Page save(Page page) throws PageNameTakenException, InvalidPageNameException;
	Page findByName(String name);
}
