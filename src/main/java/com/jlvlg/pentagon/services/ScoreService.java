package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jlvlg.pentagon.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.repositories.ScoreRepository;
import org.springframework.stereotype.Service;

/*
 * implements logic busines before call the ScoreRepository methods
 * @autor Luann
 */

@Service
public class ScoreService implements ScoreServiceInterface {
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Override
	@Transactional
	public Score save(Score score) throws ScoreOutOfAllowedException {
		if(score.getScore() > 5 || score.getScore() < -5)
			throw new ScoreOutOfAllowedException(score);
		return scoreRepository.save(score);
	}

	@Override
	public Score update(Score score) throws ScoreOutOfAllowedException {
		return scoreRepository.save(score);
	}
	@Override
	public List<Score> findByPage_User(User user) {
		return scoreRepository.findByPage_User(user);
	}

	@Override
	public List<Score> findByAuthor(User author) {
		return scoreRepository.findByAuthor(author);
	}

	@Override
	@Transactional
	public void delete(Score score) throws Exception {
		scoreRepository.delete(score);
	}
	@Override
	public Optional<Score> findById(Long id) {
		return scoreRepository.findById(id);
	}

}
