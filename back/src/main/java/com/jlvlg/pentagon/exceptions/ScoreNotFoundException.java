package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Score;

public class ScoreNotFoundException extends ObjectNotFoundException {
    private Score score;

    public ScoreNotFoundException() {
        super("Score");
    }

    public ScoreNotFoundException(Score score) {
        this();
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
