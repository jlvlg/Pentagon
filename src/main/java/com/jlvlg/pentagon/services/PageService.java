package com.jlvlg.pentagon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNameTakenException;
import com.jlvlg.pentagon.exceptions.PageNotFoundException;
import com.jlvlg.pentagon.models.Page;
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
	
	@Override
	public Optional<Page> findById(Long id) {
		return pageRepository.findById(id);
	}
	
	@Override
	public Optional<Page> findByName(String name) {
		return pageRepository.findByName(name);
	}
	
	@Override
	public Page save(Page page) throws PageNameTakenException, InvalidPageNameException {
		if (page.getName() == null ||
			page.getName().isBlank() ||
			!page.getName().matches("[a-zA-Z0-9_-]+"))
			throw new InvalidPageNameException(page);
		Optional<Page> storedPage = findByName(page.getName());
		if (storedPage.isPresent() && !storedPage.get().getId().equals(page.getId()))
			throw new PageNameTakenException(page, storedPage.get());
		return pageRepository.save(page);
	}

	@Override
	public Page update(Page page) throws PageNotFoundException, PageNameTakenException, InvalidPageNameException {
		Optional<Page> oldPage = findById(page.getId());
		if (oldPage.isEmpty())
			throw new PageNotFoundException(page);
		page.setCreationDate(oldPage.get().getCreationDate());
		return save(page);
	}

	@Override
	public void delete(Page page) throws PageNotFoundException {
		if (findById(page.getId()).isEmpty())
			throw new PageNotFoundException(page);
		pageRepository.delete(page);	
	}
}
