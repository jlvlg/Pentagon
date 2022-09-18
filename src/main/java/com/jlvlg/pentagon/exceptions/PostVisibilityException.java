/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;

/**
 * @author Lucas
 *
 */
public class PostVisibilityException extends Exception {
	private static final long serialVersionUID = 1121312434882953469L;
	private Post post;
	private User user;
	
	public PostVisibilityException(Post post, User user) {
		super("The post is already visible/invisible to the user");
		this.post = post;
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public User getUser() {
		return user;
	}
}
