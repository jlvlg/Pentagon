package com.jlvlg.pentagon.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pentagon")
public class ControllerUser {
	@Autowired
	private Pentagon pentagon;
	
	@PostMapping("user")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws InvalidUsernameException, UsernameTakenException {
		user.setJoinDate(Instant.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.saveUser(user));
	}
}
