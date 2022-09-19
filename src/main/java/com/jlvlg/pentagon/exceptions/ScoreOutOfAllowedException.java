package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Score;
/*
 * thrown when try to voting with value of out of ranged permission
 * @author Luann
 */

public class ScoreOutOfAllowedException extends Exception {
	private final Score score;
	
	
	public ScoreOutOfAllowedException(Score score) {
		super("The score cannot be greater than 5 and not less than -5");
		this.score = score;
	}
	public Score getScore() {
		return score;
	}
}
