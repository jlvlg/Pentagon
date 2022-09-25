package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;

/*
 * thrown the maximum amount of post characters
 * @author Luann
 */

public class PostMaxCharacterSizeExceededException extends Exception {
	private final Post post;
	
	public PostMaxCharacterSizeExceededException(Post post) {
		super("The text post cannot be greater than 1500 characters.");
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
