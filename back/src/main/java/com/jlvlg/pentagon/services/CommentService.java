package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/*
 * implements logic busines before call the CommentRepository methods
 * @autor Luann
 */

@Service
public class CommentService implements CommentServiceInterface {
	@Autowired private CommentRepository commentRepository;
	

	@Transactional
	public Comment save(Comment comment) throws InvalidCommentException, CommentMaxCharacterSizeExceededException {
		if(comment.getText().isEmpty() || comment.getText().isBlank()) {
			throw new InvalidCommentException(comment);
		}
		if(comment.getText().length() > 700) {
			throw new CommentMaxCharacterSizeExceededException(comment);
		}
		return commentRepository.save(comment);
	}

	public Comment update(Comment comment) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
		Optional<Comment> oldComment = findById(comment.getId());
		if(oldComment.isEmpty()) {
			throw new CommentNotFoundException(comment);
		}
		comment.setCreationDate(oldComment.get().getCreationDate());
		return save(comment);
	}

	@Transactional
	public void delete(Comment comment) throws CommentNotFoundException {
		if(findById(comment.getId()).isEmpty()) {
			throw new CommentNotFoundException(comment);
		}
		commentRepository.delete(comment);
	}

	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}

	public Slice<Comment> findByPostableAndIsActiveTrue(Postable postable, Pageable pageable) {
		return commentRepository.findByPostableAndIsActiveTrue(postable, pageable);
	}

	public long countByPostableAndIsActiveTrue(Postable postable) {
		return commentRepository.countByPostableAndIsActiveTrue(postable);
	}

	public Slice<Comment> findByAuthorAndIsActiveTrue(User author, Pageable pageable) {
		return commentRepository.findByAuthorAndIsActiveTrue(author, pageable);
	}

	public long countByAuthorAndIsActiveTrue(User author) {
		return commentRepository.countByAuthorAndIsActiveTrue(author);
	}
}
