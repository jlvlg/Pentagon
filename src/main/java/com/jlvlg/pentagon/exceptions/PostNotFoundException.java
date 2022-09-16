package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;

/**
 * Thrown when trying to access a nonexisting post
 * @author Luann
 */
public class PostNotFoundException extends Exception {
	private static final long serialVersionUID = -6266748018975873420L;
	private Post post;

	public PostNotFoundException(Post post) {
		super("Post not found.");
		this.post = post;
	}
	public Post getPost() {
		return post;
	}
}
