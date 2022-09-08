package com.jlvlg.pentagon.models;

public class SameOrderModeratorException extends InvalidOrderModeratorException {
	private Page page;
	private Moderator moderator;
	
	public SameOrderModeratorException(Page page, Moderator moderator) {
		super(moderator, "A page cannot have two moderators with the same priority order");
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	public Moderator getModerator() {
		return moderator;
	}
}
