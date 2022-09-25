package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*
 * implements logic busines before call the ScoreRepository methods
 * @autor Luann
 */

@Service
public class ScoreService implements ScoreServiceInterface {
	@Autowired
	private ScoreRepository scoreRepository;

	@Transactional
	public Score save(Score score) throws ScoreOutOfAllowedException {
		if(score.getScore() > 5 || score.getScore() < -5)
			throw new ScoreOutOfAllowedException(score);
		return scoreRepository.save(score);
	}

	public Score update(Score score) throws ScoreOutOfAllowedException {
		return save(score);
	}

	public List<Score> findByUser(User user) {
		return scoreRepository.findByPage_User(user);
	}

	public List<Score> findByAuthor(User author) {
		return scoreRepository.findByAuthor(author);
	}

	@Override
	public Optional<Score> findByPage_UserAndCategoryAndAuthor(User user, String category, User author) {
		return scoreRepository.findByPage_UserAndCategoryAndAuthor(user, category, author);
	}

	@Override
	public List<Score> findByUserAndCategory(User user, String category) {
		return scoreRepository.findByPage_UserAndCategory(user, category);
	}

	@Transactional
	public void delete(Score score) {
		scoreRepository.delete(score);
	}

	public Optional<Score> findById(Long id) {
		return scoreRepository.findById(id);
	}

}
