package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;

/*
 * Thrown when try to create a comment whose text is null or empty
 * @author Luann
 */

public class InvalidCommentException extends Exception {
	private static final long serialVersionUID = 1890544787427700594L;
	private Comment comment;

	public InvalidCommentException(Comment comment) {
		super("The text comment cannot be null or empty");
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
}
