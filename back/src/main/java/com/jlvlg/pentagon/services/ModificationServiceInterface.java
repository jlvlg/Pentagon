package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.ModificationNotFoundException;
import com.jlvlg.pentagon.exceptions.ObjectNotFoundException;
import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;

import java.util.List;

/**
 * Defines Modification specific repository access methods
 * @author Lucas
 */
public interface ModificationServiceInterface extends GenericServiceInterface<Modification, Long> {
	/**
	 * Finds all modifications of a post
	 * @param post the post to search for
	 * @return A list of all modifications
	 */
	List<Modification> findByPostable(Postable post);

	/**
	 * Saves a modification into the database
	 * @param modification The modification to be saved
	 * @return The saved modification
	 */
	@Override
	Modification save(Modification modification);

	/**
	 * Updates a modification in the database
	 * @param modification The modification to be updated
	 * @return The updated modification
	 * @throws ModificationNotFoundException Modification not found
	 */
	@Override
	Modification update(Modification modification) throws ModificationNotFoundException;

	/**
	 * Permanently drops a modification from the database
	 * @param modification The modification to be dropped
	 * @throws ModificationNotFoundException Modification not found
	 */
	@Override
	void delete(Modification modification) throws ModificationNotFoundException;

	@Override
	Modification findById(Long id) throws ModificationNotFoundException;
}
