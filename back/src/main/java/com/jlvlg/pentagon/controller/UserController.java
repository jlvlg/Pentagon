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
	@Autowired private SecurityUtils securityUtils;
	
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

	@GetMapping("search")
	public ResponseEntity<List<User>> search(String username) {
		try {
			return ResponseEntity.ok(pentagon.findUsers(username));
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
									   String username) {
		try {
			if (securityUtils.authorizeUser(token, pentagon.findUser(username).getId())) {
				pentagon.deletePage(username);
				pentagon.deleteUser(username);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (PageNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@PatchMapping
	public ResponseEntity<User> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
									   @RequestBody User user) {
		try {
			if (securityUtils.authorizeUser(token, user.getId())) {
				return ResponseEntity.ok(pentagon.updateUser(user));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UsernameTakenException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (InvalidUsernameException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@PatchMapping("follow")
	public ResponseEntity<Void> followUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
										   Long followingId, Long followedId) {
		try {
			if (securityUtils.authorizeUser(token, followingId)) {
				pentagon.followUser(followingId, followedId);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UserAlreadyFollowedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (UsernameTakenException | InvalidUsernameException e) {
			throw new RuntimeException(e);
		}
	}

	@PatchMapping("unfollow")
	public ResponseEntity<Void> unfollowUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
											 Long followingId, Long followedId) {
		try {
			if (securityUtils.authorizeUser(token, followingId)) {
				pentagon.unfollowUser(followingId, followedId);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (UserNotFollowedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (UsernameTakenException | InvalidUsernameException e) {
			throw new RuntimeException(e);
		}
	}

	@PatchMapping("activation")
	public ResponseEntity<Void> switchIsActive(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
											   User user) {
		try {
			if (securityUtils.authorizeUser(token, user.getId())) {
				pentagon.switchUserIsActive(user);
				pentagon.switchPageIsActive(pentagon.findPage(user));
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} catch (UserNotFoundException | PageNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
