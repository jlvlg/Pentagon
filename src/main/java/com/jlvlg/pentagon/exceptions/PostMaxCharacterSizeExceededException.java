package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;

/*
 * thrown the maximum amount of post caracteres
 * @author Luann
 */

public class PostMaxCharacterSizeExceededException extends Exception {
	private static final long serialVersionUID = -3505705663618291473L;
	private Post post;
	
	public PostMaxCharacterSizeExceededException(Post post) {
		super("The text post cannot be greater than 1500 characteres.");
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
