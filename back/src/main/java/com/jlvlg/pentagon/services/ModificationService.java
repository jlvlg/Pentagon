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

@Service
public class ModificationService implements ModificationServiceInterface {
	@Autowired private ModificationRepository modificationRepository;

	public Modification findById(Long id) throws ModificationNotFoundException {
		Optional<Modification> modification = modificationRepository.findById(id);
		if (modification.isEmpty())
			throw new ModificationNotFoundException();
		return modification.get();
	}

	public List<Modification> findByPostable(Postable post) {
		return modificationRepository.findByPostableOrderByDateDesc(post);
	}

	public Modification save(Modification modification) {
		return modificationRepository.save(modification);
	}

	public Modification update(Modification modification) throws ModificationNotFoundException {
		Modification oldModification = findById(modification.getId());
		modification.setDate(oldModification.getDate());
		return save(modification);
	}

	public void delete(Modification modification) throws ModificationNotFoundException {
		findById(modification.getId());
		modificationRepository.delete(modification);
	}

}
