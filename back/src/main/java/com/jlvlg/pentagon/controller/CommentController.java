package com.jlvlg.pentagon.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jlvlg.pentagon.exceptions.CommentMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.CommentNotFoundException;
import com.jlvlg.pentagon.exceptions.InvalidCommentException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;

@RestController("comments")
public class CommentController {
	@Autowired
	private Pentagon pentagon;

	@PostMapping
	public ResponseEntity<Comment> save(@RequestBody Comment comment) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.saveComment(comment));
        } catch (InvalidCommentException | CommentMaxCharacterSizeExceededException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

	@GetMapping
	public ResponseEntity<Comment> load (Long id) {
        try {
            return ResponseEntity.ok(pentagon.findComment(id));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@GetMapping("/load")
	public ResponseEntity<List<Comment>> loadComments(@RequestBody Postable postable) {
        try {
            return ResponseEntity.ok(pentagon.loadComments(postable));
        } catch (PostNotFoundException | CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody Comment comment ) {
        try {
            pentagon.deleteComment(comment);
            return ResponseEntity.noContent().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@PatchMapping
	public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        try {
            return ResponseEntity.ok(pentagon.updateComment(comment));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidCommentException | CommentMaxCharacterSizeExceededException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

	@PatchMapping("like")
	public ResponseEntity<Void> like (Comment comment) {
        try {
            pentagon.likeComment(comment);
            return ResponseEntity.noContent().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
	}

	@PatchMapping("unlike")
    public ResponseEntity<Void> unlike (Comment comment) {
        try {
            pentagon.unlikeComment(comment);
            return ResponseEntity.noContent().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@PatchMapping("edit")
	public ResponseEntity<Void> edit (@RequestBody Comment comment) {
        try {
            pentagon.editComment(comment);
            return ResponseEntity.noContent().build();
        } catch (CommentMaxCharacterSizeExceededException | InvalidCommentException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
	}
}
