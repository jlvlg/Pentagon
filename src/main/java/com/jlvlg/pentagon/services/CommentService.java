package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.CommentRepository;

/*
 * implements logic busines before call the CommentRepository methods
 * @autor Luann
 */

@Service
public class CommentService implements CommentServiceInterface{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
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
	@Override
	public Comment update(Comment comment) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
		Optional<Comment> oldComment = findById(comment.getId());
		if(!oldComment.isPresent()) {
			throw new CommentNotFoundException(comment);
		}
		comment.setCreationDate(oldComment.get().getCreationDate());
		return save(comment);
	}
	@Override
	@Transactional
	public void delete(Comment comment) throws CommentNotFoundException {
		if(findById(comment.getId()).isEmpty()) {
			throw new CommentNotFoundException(comment);
		}
		commentRepository.delete(comment);
	}
	@Override
	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}
	@Override
	public List<Comment> findByPostableAndActiveTrue(Postable post) {
		return commentRepository.findByPostableAndActiveTrue(post);
	}
	@Override
	public List<Comment> findByAuthorAndActiveTrue(User author) {
		return commentRepository.findByAuthorAndActiveTrue(author);
	}
}
