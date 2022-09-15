package com.jlvlg.pentagon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;

public interface CommentServiceInterface extends GenericServiceInterface <Comment, Long> {
	List<Comment> findByPostableAndActiveTrue(Postable post);
	List<Comment> findByAuthorAndActiveTrue(User author);
}
