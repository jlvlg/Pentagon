package com.jlvlg.pentagon.services;

import java.util.Optional;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNotFoundException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.User;

/**
 * Defines Page specific repository access methods
 * @author Lucas
 *
 */
public interface PageServiceInterface extends GenericServiceInterface<Page, Long>{
	/**
	 * Finds a page by user
	 * @param user the page's user
	 * @return An optional that might contain the page
	 */
	Optional<Page> findByUser(User user);
	
	/**
	 * Saves a page to the database
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
	 */
	@Override
	Page save(Page page) throws InvalidPageNameException;
	
	/**
	 * Updates a page in the database
	 * @throws PageNotFoundException Page not found
	 */
	@Override
	Page update(Page page) throws PageNotFoundException, InvalidPageNameException;
	
	/**
	 * Permanently drops a page from the database
	 * @throws PageNotFoundException Page not found
	 */
	@Override
	void delete(Page page) throws PageNotFoundException;
}
