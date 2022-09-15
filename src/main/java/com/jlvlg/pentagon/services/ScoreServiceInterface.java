package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Score;

public interface ScoreServiceInterface extends GenericServiceInterface <Score, Long> {
	List<Score> findByProfileAndActiveTrue(Profile profile);
	//List<Score> findByProfileAndCategoryAndActiveTrue(Profile profile);
}
