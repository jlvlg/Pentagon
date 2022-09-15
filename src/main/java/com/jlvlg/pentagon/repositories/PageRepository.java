package com.jlvlg.pentagon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
	Optional<Page> findByName(String name);
}
