package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines methods to read and write to the Score database
 * @author Luann
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
	List<Score> findByPage_User(User user);
	List<Score> findByAuthor(User author);
}
