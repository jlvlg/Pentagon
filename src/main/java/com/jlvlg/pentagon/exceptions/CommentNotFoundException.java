package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Comment;
/*
 * Thrown when try to acess a nonexistent comment
 * @author Luann
 */

public class CommentNotFoundException extends Exception {
	private static final long serialVersionUID = 6409062341816722248L;
	private Comment comment;
	
	public CommentNotFoundException(Comment comment) {
		super ("The comment cannot be found");
		this.comment = comment;
	}
	public Comment getComment() {
		return comment;
	}
	
}
