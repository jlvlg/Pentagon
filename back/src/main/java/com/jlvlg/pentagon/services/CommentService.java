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
import java.util.List;
import java.util.Optional;

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
		Comment oldComment = findById(comment.getId());
		comment.setCreationDate(oldComment.getCreationDate());
		return save(comment);
	}

	@Transactional
	public void delete(Comment comment) throws CommentNotFoundException {
		findById(comment.getId());
		commentRepository.delete(comment);
	}

	public Comment findById(Long id) throws CommentNotFoundException {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty())
			throw new CommentNotFoundException();
		return comment.get();
	}

	public List<Comment> findByPostable(Postable postable) {
		return commentRepository.findByPostable(postable);
	}

	public List<Comment> findByAuthor(User author) {
		return commentRepository.findByAuthor(author);
	}
}
