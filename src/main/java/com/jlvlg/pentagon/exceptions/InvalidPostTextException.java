package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;
/*
 * Thrown when try to create a post whose text is null or empty
 * @author Luann
 */

public class InvalidPostTextException extends Exception {
	private static final long serialVersionUID = -6457638317903891194L;
	private Post post;

	public InvalidPostTextException(Post post) {
		super("A text post cannot be null or empty.");
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
