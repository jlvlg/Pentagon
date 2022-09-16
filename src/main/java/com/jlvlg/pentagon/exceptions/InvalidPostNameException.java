package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;
/*
 * Thrown when try to create a post whose title is null or empty
 * @author Luann
 */

public class InvalidPostNameException extends Exception {
	private static final long serialVersionUID = 3427285910433893741L;
	private Post post;
	
	public InvalidPostNameException(Post post) {
		super("A tittle post cannot be null or empty.");
		this.post = post;
	}
	
	public Post getPost() {
		return post;
	}
	
}
