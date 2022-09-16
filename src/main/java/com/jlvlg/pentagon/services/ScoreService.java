package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.repositories.ScoreRepository;

/*
 * implements logic busines before call the ScoreRepository methods
 * @autor Luann
 */

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
	public List<Score> findByProfileAndActiveTrue(Profile profile) {
		return scoreRepository.findByProfileAndActiveTrue(profile);
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
