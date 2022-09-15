package com.jlvlg.pentagon.services;

import java.util.Optional;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNameTakenException;
import com.jlvlg.pentagon.exceptions.PageNotFoundException;
import com.jlvlg.pentagon.models.Page;

/**
 * Defines Page specific repository access methods
 * @author Lucas
 *
 */
public interface PageServiceInterface extends GenericServiceInterface<Page, Long>{
	/**
	 * Finds a page by name
	 * @param name the page's name
	 * @return An optional that might contain the page
	 */
	Optional<Page> findByName(String name);
	
	/**
	 * Saves a page to the database
	 * @throws PageNameTakenException Two pages cannot have the same name
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain spaces and/or special characters
	 */
	@Override
	Page save(Page page) throws PageNameTakenException, InvalidPageNameException;
	
	/**
	 * Updates a page in the database
	 * @throws PageNotFoundException Page not found
	 * @throws InvalidPageNameException Two pages cannot have the same name
	 * @throws PageNameTakenException page name cannot be null, empty, or contain spaces and/or special characters
	 */
	@Override
	Page update(Page page) throws PageNotFoundException, PageNameTakenException, InvalidPageNameException;
	
	/**
	 * Permanently drops a page from the database
	 * @throws PageNotFoundException Page not found
	 */
	@Override
	void delete(Page page) throws PageNotFoundException;
}
