package com.jlvlg.pentagon.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.PageNameTakenException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.repositories.PageRepository;

@Service
public class PageService implements PageServiceInterface {
	@Autowired
	PageRepository pageRepository;
	
	@Override
	public Page save(Page page) throws PageNameTakenException, InvalidPageNameException {
//		if (page.getName() == null || page.getName().isEmpty() || page.getName().matches(""))
//			throw new InvalidPageNameException(page);		
		Optional<Page> storedPage = pageRepository.findByName(page.getName());
		if (storedPage.isPresent())
			throw new PageNameTakenException(page, storedPage.get());
		return null;
	}

	@Override
	public Page findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page update(Page object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page remove(Page object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
