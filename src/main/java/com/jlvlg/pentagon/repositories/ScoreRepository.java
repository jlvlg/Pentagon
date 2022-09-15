package com.jlvlg.pentagon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
	List<Score> findByProfileAndActiveTrue(Profile profile);
	//List<Score> findByProfileAndByCategoryAndActiveTrue(Profile profile);
}
