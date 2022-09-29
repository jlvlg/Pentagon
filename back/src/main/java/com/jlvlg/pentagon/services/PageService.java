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

	public Page findById(Long id) throws PageNotFoundException {
		Optional<Page> page = pageRepository.findById(id);
		if (page.isEmpty())
			throw new PageNotFoundException();
		return page.get();
	}

	public Page findByUser(User user) throws PageNotFoundException {
		Optional<Page> page = pageRepository.findByUserAndActiveTrue(user);
		if (page.isEmpty())
			throw new PageNotFoundException();
		return page.get();
	}

	public Page save(Page page) throws InvalidPageNameException {
		if (page.getName() == null ||
			page.getName().isBlank() ||
			!page.getName().matches("[ a-zA-Z0-9_.-]+"))
			throw new InvalidPageNameException(page);
		return pageRepository.save(page);
	}

	public Page update(Page page) throws PageNotFoundException, InvalidPageNameException {
		Page oldPage = findById(page.getId());
		return save(page);
	}

	public void delete(Page page) throws PageNotFoundException {
		findById(page.getId());
		pageRepository.delete(page);	
	}
}
