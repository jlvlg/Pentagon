package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;

/**
 * Thrown when trying to access a nonexistent post
 * @author Luann
 */
public class PostNotFoundException extends ObjectNotFoundException {
	private Post post;

	public PostNotFoundException() {
		super("Post");
	}

	public PostNotFoundException(Post post) {
		this();
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
