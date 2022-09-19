package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines methods to read and write to the Modification database
 * @author Lucas
 *
 */
@Repository
public interface ModificationRepository extends JpaRepository<Modification, Long> {
	List<Modification> findByPostableOrderByDateDesc(Postable postable);
}
