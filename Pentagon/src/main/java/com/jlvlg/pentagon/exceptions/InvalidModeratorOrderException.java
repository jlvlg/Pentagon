package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Moderator;

/**
 * Thrown when a moderator's order attribute assumes an invalid value
 * @author Lucas
 * 
 */
public class InvalidModeratorOrderException extends Exception {
	private static final long serialVersionUID = -6785070131125232362L;
	private Moderator moderator;

	public InvalidModeratorOrderException(Moderator moderator) {
		this(moderator, "Moderator order has to be greater than or equals to one");
	}
	
	public InvalidModeratorOrderException(Moderator moderator, String errorMessage) {
		super(errorMessage);
		this.moderator = moderator;
	}

	public Moderator getModerator() {
		return moderator;
	}
}
