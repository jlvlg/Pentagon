package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;
/*
 * Thrown when try to access a nonexistent comment
 * @author Luann
 */

public class CommentNotFoundException extends ObjectNotFoundException {
	private Comment comment;

	public CommentNotFoundException() {
		super("Comment");
	}

	public CommentNotFoundException(Comment comment) {
		this();
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
	
}
