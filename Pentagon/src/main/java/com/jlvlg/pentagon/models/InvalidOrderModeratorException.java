package com.jlvlg.pentagon.models;

public class InvalidOrderModeratorException extends Exception {
	private Moderator moderator;

	public InvalidOrderModeratorException(Moderator moderator) {
		this(moderator, "Moderator order has to be greater than or equals to one");
	}
	
	public InvalidOrderModeratorException(Moderator moderator, String errorMessage) {
		super(errorMessage);
		this.moderator = moderator;
	}

	public Moderator getModerator() {
		return moderator;
	}
}
