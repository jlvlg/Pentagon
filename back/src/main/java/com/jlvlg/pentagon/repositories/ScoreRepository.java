package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Defines methods to read and write to the Score database
 * @author Luann
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
	List<Score> findByProfile(Profile profile);
	List<Score> findByAuthor(User author);
	List<Score> findByProfileAndAuthor(Profile profile, User author);
	Optional<Score> findByProfileAndCategoryAndAuthor(Profile profile, String category, User author);
	List<Score> findByProfileAndCategory(Profile profile, String category);
}
