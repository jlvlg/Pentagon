package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;

public interface ScoreServiceInterface extends GenericServiceInterface <Score, Long> {
	List<Score> findByPage_User(User page);

	List<Score> findByAuthor(User author);
}
