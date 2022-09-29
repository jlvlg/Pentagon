package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.exceptions.ObjectNotFoundException;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentServiceInterface extends GenericServiceInterface <Comment, Long> {
	Slice<Comment> findByPostableAndIsActiveTrue(Postable postable, Pageable pageable);
	long countByPostableAndIsActiveTrue(Postable postable);
	Slice<Comment> findByAuthorAndIsActiveTrue(User author, Pageable pageable);
	long countByAuthorAndIsActiveTrue(User author);

	@Override
	Comment save(Comment object) throws InvalidCommentException, CommentMaxCharacterSizeExceededException;

	@Override
	Comment update(Comment object) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException;

	@Override
	void delete(Comment object) throws CommentNotFoundException;

	@Override
	Comment findById(Long id) throws CommentNotFoundException;
}
