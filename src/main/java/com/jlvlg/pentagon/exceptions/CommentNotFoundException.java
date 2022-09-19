package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;
/*
 * Thrown when try to access a nonexistent comment
 * @author Luann
 */

public class CommentNotFoundException extends Exception {
	private final Comment comment;
	
	public CommentNotFoundException(Comment comment) {
		super ("The comment cannot be found");
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
	
}
