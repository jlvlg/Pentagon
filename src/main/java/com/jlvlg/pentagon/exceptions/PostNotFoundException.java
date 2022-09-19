package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;

/**
 * Thrown when trying to access a nonexistent post
 * @author Luann
 */
public class PostNotFoundException extends Exception {
	private final Post post;

	public PostNotFoundException(Post post) {
		super("Post not found.");
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
