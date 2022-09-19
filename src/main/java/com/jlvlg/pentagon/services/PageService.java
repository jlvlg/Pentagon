package com.jlvlg.pentagon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNotFoundException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.PageRepository;

/**
 * Implements business logic before calling the PageRepository methods
 * @author Lucas
 *
 */
@Service
public class PageService implements PageServiceInterface {
	@Autowired
	private PageRepository pageRepository;

	public Optional<Page> findById(Long id) {
		return pageRepository.findById(id);
	}

	/**
	 * Finds a page by user
	 * @param user the page's user
	 * @return An optional that might contain the page
	 */
	public Optional<Page> findByUser(User user) {
		return pageRepository.findByUser(user);
	}

	/**
	 * Saves a page to the database
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
	 */
	public Page save(Page page) throws InvalidPageNameException {
		if (page.getName() == null ||
			page.getName().isBlank() ||
			!page.getName().matches("[ a-zA-Z0-9_-]+"))
			throw new InvalidPageNameException(page);
		return pageRepository.save(page);
	}

	/**
	 * Updates a page in the database
	 * @throws PageNotFoundException Page not found
	 * @throws InvalidPageNameException A page name cannot be null, empty, or contain special characters
	 */
	public Page update(Page page) throws PageNotFoundException, InvalidPageNameException {
		Optional<Page> oldPage = findById(page.getId());
		if (oldPage.isEmpty())
			throw new PageNotFoundException(page);
		return save(page);
	}

	/**
	 * Permanently drops a page from the database
	 * @throws PageNotFoundException Page not found
	 */
	public void delete(Page page) throws PageNotFoundException {
		if (findById(page.getId()).isEmpty())
			throw new PageNotFoundException(page);
		pageRepository.delete(page);	
	}
}
