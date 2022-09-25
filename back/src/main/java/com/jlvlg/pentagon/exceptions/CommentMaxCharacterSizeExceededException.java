package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;
/*
 * thrown the maximum amount of comment characters
 * @author Luann
 */

public class CommentMaxCharacterSizeExceededException extends Exception {
	private final Comment comment;

	public CommentMaxCharacterSizeExceededException(Comment comment) {
		super("The text post cannot be greater than 700 characters");
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
	
	
}
