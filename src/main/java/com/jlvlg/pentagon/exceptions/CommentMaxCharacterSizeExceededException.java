package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;
/*
 * thrown the maximum amount of comment caracteres
 * @author Luann
 */

public class CommentMaxCharacterSizeExceededException extends Exception {
	private static final long serialVersionUID = -2796947686127262018L;
	private Comment comment;

	public CommentMaxCharacterSizeExceededException(Comment comment) {
		super("The text post cannot be greater than 700 characteres");
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
	
	
}
