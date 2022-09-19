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
	Optional<Page> findByUser(User user);

	@Override
	Page save(Page object) throws InvalidPageNameException;

	@Override
	Page update(Page object) throws PageNotFoundException, InvalidPageNameException;

	@Override
	void delete(Page object) throws PageNotFoundException;
}
