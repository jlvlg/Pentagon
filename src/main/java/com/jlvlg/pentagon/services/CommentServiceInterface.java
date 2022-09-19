package com.jlvlg.pentagon.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;

public interface CommentServiceInterface extends GenericServiceInterface <Comment, Long> {
	List<Comment> findByPostableAndActiveTrue(Postable post, Pageable pageable);
	List<Comment> findByAuthorAndActiveTrue(User author, Pageable pageable);
}
