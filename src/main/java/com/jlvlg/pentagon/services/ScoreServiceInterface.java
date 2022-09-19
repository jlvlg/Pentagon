package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;

import java.util.List;

public interface ScoreServiceInterface extends GenericServiceInterface <Score, Long> {
	List<Score> findByUser(User page);

	List<Score> findByAuthor(User author);

	@Override
	Score save(Score object) throws ScoreOutOfAllowedException;

	@Override
	Score update(Score object) throws ScoreOutOfAllowedException;
}
