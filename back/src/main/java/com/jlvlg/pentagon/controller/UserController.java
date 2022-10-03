package com.jlvlg.pentagon.controller;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired private Pentagon pentagon;
	
	@GetMapping
	public ResponseEntity<User> getUser(Optional<String> username, Optional<Long> id) {
		try {
			if (username.isPresent()) {
				return ResponseEntity.ok(pentagon.findUser(username.get()));
			} else if (id.isPresent()) {
				return ResponseEntity.ok(pentagon.findUser(id.get()));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(String username) {
		try {
			pentagon.deleteUser(username);
			return ResponseEntity.noContent().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping
	public ResponseEntity<User> update(@RequestBody User user) {
		try {
			return ResponseEntity.ok(pentagon.updateUser(user));
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UsernameTakenException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (InvalidUsernameException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PatchMapping("follow")
	public ResponseEntity<Integer> switchFollowUser(Long followingId, Long followedId) {
		try {
			pentagon.followUser(followingId, followedId);
			return ResponseEntity.ok(0);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UserAlreadyFollowedException e) {
			try {
				pentagon.unfollowUser(followingId, followedId);
				return ResponseEntity.ok(1);
			} catch (UserNotFoundException |
					 UserNotFollowedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
