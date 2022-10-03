package com.jlvlg.pentagon.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.exceptions.PostVisibilityException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;

@RestController
@RequestMapping("posts")
public class PostController {
	@Autowired
	private Pentagon pentagon;

	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.savePost(post));
        } catch (PostMaxCharacterSizeExceededException | InvalidPostNameException | InvalidPostTextException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

	@GetMapping
	public ResponseEntity<List<Post>> getPosts(Optional<Long> id, Optional<Long> author) {
		try {
            if (id.isPresent()) {
                return ResponseEntity.ok(List.of(pentagon.findPost(id.get())));
            } else if (author.isPresent()) {
                return ResponseEntity.ok(pentagon.loadPosts(author.get()));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (PostNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("followed")
	public ResponseEntity<List<Post>> followingPosts(Long user) {
		try {
			return ResponseEntity.ok(pentagon.followingPosts(user));
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody Post post) {
		try {
            pentagon.deletePost(post);
            return ResponseEntity.noContent().build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
	}

	@PatchMapping
	public ResponseEntity<Post> update(@RequestBody Post post) {
		try {
			return ResponseEntity.ok(pentagon.updatePost(post));
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (InvalidPostNameException | InvalidPostTextException | PostMaxCharacterSizeExceededException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PatchMapping("like")
	public ResponseEntity<Void> like (@RequestBody Post post) {
		try {
			pentagon.likePost(post);
			return ResponseEntity.noContent().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("unlike")
	public ResponseEntity<Void> unlike (@RequestBody Post post) {
		try {
			pentagon.unlikePost(post);
			return ResponseEntity.noContent().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("edit")
	public ResponseEntity<Void> edit(@RequestBody Post post) {
		try {
			pentagon.editPost(post);
			return ResponseEntity.noContent().build();
		} catch (PostMaxCharacterSizeExceededException | InvalidPostNameException | InvalidPostTextException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@PatchMapping("visible")
	public ResponseEntity<Boolean> turnPostVisibleTo(@RequestBody ObjectNode body) {
		try {
			return ResponseEntity.ok(
					pentagon.turnPostVisibleTo(
							new ObjectMapper().treeToValue(body.get("user"), User.class),
							new ObjectMapper().treeToValue(body.get("post"), Post.class)
					)
			);
		} catch (PostVisibilityException | JsonProcessingException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("invisible")
	public ResponseEntity<Boolean> turnPostInvisibleTo(@RequestBody ObjectNode body) {
		try {
			return ResponseEntity.ok(
					pentagon.turnPostInvisibleTo(
							new ObjectMapper().treeToValue(body.get("user"), User.class),
							new ObjectMapper().treeToValue(body.get("post"), Post.class)
					)
			);
		} catch (PostVisibilityException | JsonProcessingException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (PostNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
