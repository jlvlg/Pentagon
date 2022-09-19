/**
 * 
 */
package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.ModificationNotFoundException;
import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.repositories.ModificationRepository;

/**
 * Implements business logic before calling the ModificationRepository methods
 * @author Lucas
 *
 */
@Service
public class ModificationService implements ModificationServiceInterface {
	@Autowired
	private ModificationRepository modificationRepository;

	public Optional<Modification> findById(Long id) {
		return modificationRepository.findById(id);
	}

	public List<Modification> findByPostable(Postable post) {
		return modificationRepository.findByPostableOrderByDateDesc(post);
	}

	public Modification save(Modification modification) {
		return modificationRepository.save(modification);
	}

	public Modification update(Modification modification) throws ModificationNotFoundException {
		Optional<Modification> oldModification = findById(modification.getId());
		if (oldModification.isEmpty())
			throw new ModificationNotFoundException(modification);
		modification.setDate(oldModification.get().getDate());
		return save(modification);
	}

	public void delete(Modification modification) throws ModificationNotFoundException {
		if (findById(modification.getId()).isEmpty())
			throw new ModificationNotFoundException(modification);
		modificationRepository.delete(modification);
	}

}
