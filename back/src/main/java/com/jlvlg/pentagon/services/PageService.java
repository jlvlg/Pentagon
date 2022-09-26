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
	@Autowired private PageRepository pageRepository;

	public Optional<Page> findById(Long id) {
		return pageRepository.findById(id);
	}

	public Optional<Page> findByUser(User user) {
		return pageRepository.findByUser(user);
	}

	public Page save(Page page) throws InvalidPageNameException {
		if (page.getName() == null ||
			page.getName().isBlank() ||
			!page.getName().matches("[ a-zA-Z0-9_-]+"))
			throw new InvalidPageNameException(page);
		return pageRepository.save(page);
	}

	public Page update(Page page) throws PageNotFoundException, InvalidPageNameException {
		Optional<Page> oldPage = findById(page.getId());
		if (oldPage.isEmpty())
			throw new PageNotFoundException(page);
		return save(page);
	}

	public void delete(Page page) throws PageNotFoundException {
		if (findById(page.getId()).isEmpty())
			throw new PageNotFoundException(page);
		pageRepository.delete(page);	
	}
}
