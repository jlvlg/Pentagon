package com.jlvlg.pentagon.controller;

import com.jlvlg.pentagon.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired private Pentagon pentagon;

	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user) throws InvalidUsernameException, UsernameTakenException, InvalidPageNameException {
		return ResponseEntity.ok(pentagon.saveUser(user));
	}
	
	@GetMapping()
	public ResponseEntity<User> find(String username, Long id) {

		return ResponseEntity.ok(pentagon.findUser(username));
	}
//
//	@GetMapping("/search")
//	public ResponseEntity<User> findById(@PathVariable (value = "id") long id) throws UserNotFoundException {
//		return ResponseEntity.ok(pentagon.findUser(id));
//	}
//
//	@GetMapping("/search")
//	public ResponseEntity<List<User>> loadUsers(@PathVariable (value = "username") String username) throws UserNotFoundException {
//		return ResponseEntity.ok(pentagon.findUsers(username));
//	}
//
//	@DeleteMapping("/delete/{user}")
//	public void delete(@PathVariable (value = "user") User user) throws UserNotFoundException {
//		pentagon.deleteUser(user);
//	}
//
//	@PutMapping("/update/{user}")
//	public ResponseEntity<User> update(@PathVariable (value = "user") User user) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException {
//		return ResponseEntity.ok(pentagon.updateUser(user));
//	}
//
//	@PutMapping
//	public void followUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException {
//		pentagon.followUser(followingId, followedId);
//	}
//
//	@PutMapping
//	public void unfollowUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException, UserNotFollowedException {
//		pentagon.unfollowUser(followingId, followedId);
//	}
}
