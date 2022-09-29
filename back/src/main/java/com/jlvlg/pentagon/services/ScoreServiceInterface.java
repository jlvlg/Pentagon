package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.ObjectNotFoundException;
import com.jlvlg.pentagon.exceptions.ScoreNotFoundException;
import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;

import java.util.List;
import java.util.Optional;

public interface ScoreServiceInterface extends GenericServiceInterface <Score, Long> {
	List<Score> findByUser(User user);

	List<Score> findByAuthor(User author);

	Optional<Score> findByPage_UserAndCategoryAndAuthor(User user, String category, User author);

	List<Score> findByUserAndCategory(User user, String category);

	@Override
	Score save(Score object) throws ScoreOutOfAllowedException;

	@Override
	Score update(Score object) throws ScoreOutOfAllowedException;

	@Override
	void delete(Score object) throws ScoreNotFoundException;

	@Override
	Score findById(Long id) throws ScoreNotFoundException;
}
