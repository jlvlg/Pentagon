package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;

public interface ModificationServiceInterface extends GenericServiceInterface<Modification, Long> {
	List<Modification> findByPost(Postable post);
}
