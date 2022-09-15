package com.jlvlg.pentagon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Modification;
import com.jlvlg.pentagon.models.Postable;

@Repository
public interface ModificationRepository extends JpaRepository<Modification, Long> {
	List<Modification> findByPostOrderByDateDesc(Postable post);
}
