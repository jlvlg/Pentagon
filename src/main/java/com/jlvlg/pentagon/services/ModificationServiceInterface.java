package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.ModificationNotFoundException;
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
	Modification update(Modification object) throws ModificationNotFoundException;

	@Override
	void delete(Modification object) throws ModificationNotFoundException;
}
