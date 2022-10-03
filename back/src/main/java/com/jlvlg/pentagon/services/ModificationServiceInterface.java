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
	List<Modification> findByPostable(Postable post);

	@Override
	Modification save(Modification modification);

	@Override
	Modification update(Modification modification) throws ModificationNotFoundException;

	@Override
	void delete(Modification modification) throws ModificationNotFoundException;

	@Override
	Modification findById(Long id) throws ModificationNotFoundException;
}
