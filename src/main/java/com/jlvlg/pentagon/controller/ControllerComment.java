package com.jlvlg.pentagon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/pentagon/post/comments")
public class ControllerComment {
	@Autowired
	private Pentagon pentagon;
	
	@PostMapping
	public ResponseEntity<Comment> save(@RequestBody Comment comment) throws InvalidCommentException, CommentMaxCharacterSizeExceededException {
		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.saveComment(comment));
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Comment> load (@PathVariable (value = "id") long id) throws CommentNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findComment(id));
	}
	
	@GetMapping("/search/{postable}/")
	public ResponseEntity<List<Comment>> loadComments(@PathVariable (value = "postasble") Postable postable, int numberPage) throws CommentNotFoundException, PostNotFoundException {
		List<Comment> load = pentagon.loadComments(postable, numberPage);
		return ResponseEntity.status(HttpStatus.OK).body(load);
	}
	
	@DeleteMapping("/delete/{comment}")
	public void delete(@PathVariable (value = "comment") Comment comment ) throws CommentNotFoundException {
		pentagon.deleteComment(comment);
	}
	
	@PutMapping("/update/{comment}")
	public ResponseEntity<Comment> update(@PathVariable (value = "comment") Comment comment) throws CommentNotFoundException, InvalidCommentException, CommentMaxCharacterSizeExceededException {
		return ResponseEntity.status(HttpStatus.OK).body(pentagon.updateComment(comment));
	}
	
	@PutMapping
	public void like (Comment comment) throws CommentNotFoundException, CommentMaxCharacterSizeExceededException, InvalidCommentException {
		pentagon.likeComment(comment);
	}
	
	@PutMapping
	public void unlike (Comment comment) throws CommentNotFoundException, CommentMaxCharacterSizeExceededException, InvalidCommentException {
		pentagon.likeComment(comment);
	}
	
	@PutMapping("/editing/{comment}")
	public void edit (@PathVariable (value = "comment") Comment comment, String text) throws CommentNotFoundException, CommentMaxCharacterSizeExceededException, InvalidCommentException {
		pentagon.editComment(comment, text);
	}	
}

