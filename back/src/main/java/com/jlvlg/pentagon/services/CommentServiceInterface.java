package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CommentServiceInterface extends GenericServiceInterface <Comment, Long> {
	List<Comment> findByPostable(Postable postable);
	List<Comment> findByAuthor(User author);

	@Override
	Comment save(Comment object) throws InvalidCommentException, CommentMaxCharacterSizeExceededException;

	@Override
	Comment update(Comment object) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException;

	@Override
	void delete(Comment object) throws CommentNotFoundException;

	@Override
	Comment findById(Long id) throws CommentNotFoundException;
}
