package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Followable;

/**
 * Thrown when trying to decrease the followers attribute of a Followable inherited class
 * instance when it's already at 0
 * @author Lucas
 *
 */
public class NegativeFollowersException extends Exception {
	private static final long serialVersionUID = -2799864130306257759L;
	private Followable followable;

	public NegativeFollowersException(Followable followable) {
		super("Followers attribute must be greater than or equals to 0");
		this.followable = followable;
	}

	public Followable getFollowable() {
		return followable;
	}
}
