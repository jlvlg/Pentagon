package com.jlvlg.pentagon.controller;

import java.time.Instant;
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

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserAlreadyFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pentagon/user")
public class ControllerUser {
	@Autowired
	private Pentagon pentagon;
	
//	@PostMapping("user")
//	public ResponseEntity<User> saveUser(@RequestBody User user) throws InvalidUsernameException, UsernameTakenException {
//		user.setJoinDate(Instant.now());
//		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.saveUser(user));
//	}
	
	@GetMapping("/search/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable (value = "username") String username) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findUser(username));
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<User> findById(@PathVariable (value = "id") long id) throws UserNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findUser(id));
	}
	
	@GetMapping("/search/{username}")
	public ResponseEntity<List<User>> loadUsers(@PathVariable (value = "username") String username) throws UserNotFoundException {
		List<User> users = pentagon.findUsers(username);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@DeleteMapping("/delete/{user}")
	public void delete(@PathVariable (value = "user") User user) throws UserNotFoundException {
		pentagon.deleteUser(user);
	}
	
	@PutMapping("/update/{user}")
	public ResponseEntity<User> update(@PathVariable (value = "user") User user) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException {
		return ResponseEntity.status(HttpStatus.OK).body(pentagon.updateUser(user));
	}
	
	@PutMapping
	public void followUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException {
		pentagon.followUser(followingId, followedId);
	}
	
	@PutMapping
	public void unfollowUser(Long followingId, Long followedId) throws UserNotFoundException, UsernameTakenException, InvalidUsernameException, UserAlreadyFollowedException, UserNotFollowedException {
		pentagon.unfollowUser(followingId, followedId);
	}
	
	
}
