package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.exceptions.ModificationNotFoundException;
import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;

public interface ModificationServiceInterface extends GenericServiceInterface<Modification, Long> {
	List<Modification> findByPost(Postable post);

	/**
	 * Saves a post into the database
	 */
	@Override
	Modification save(Modification modification);

	/**
	 * Updates a post in the database
	 * @throws ModificationNotFoundException Modification not found
	 */
	@Override
	Modification update(Modification modification) throws ModificationNotFoundException;

	/**
	 * Permanently drops a post from the database
	 * @throws ModificationNotFoundException Modification not found
	 */
	@Override
	void delete(Modification modification) throws ModificationNotFoundException;
}
