package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNotFoundException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.User;

import java.util.Optional;

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
	 * @param page The page to be saved
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
	 */
	@Override
	Page save(Page page) throws InvalidPageNameException;

	/**
	 * Updates a page in the database
	 * @param page The paeg to be updated
	 * @throws PageNotFoundException Page not found
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
	 */
	@Override
	Page update(Page page) throws PageNotFoundException, InvalidPageNameException;

	/**
	 * Permanently drops a page from the database
	 * @param page The page to be deleted
	 * @throws PageNotFoundException Page not found
	 */
	@Override
	void delete(Page page) throws PageNotFoundException;
}
