package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;
/*
 * Thrown when try to create a post whose title is null or empty
 * @author Luann
 */

public class InvalidPostNameException extends Exception {
	private final Post post;
	
	public InvalidPostNameException(Post post) {
		super("A tittle post cannot be null or empty.");
		this.post = post;
	}
	
	public Post getPost() {
		return post;
	}
	
}
