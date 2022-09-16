/**
 * 
 */
package com.jlvlg.pentagon.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.UserRepository;

/**
 * Implements business logic before calling the UserRepository methods
 * @author Lucas
 *
 */
@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findById(Long id) {
		//TODO DECRYPT THE USER'S PASSWORD >:(
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		//TODO HERE TOOOOOO!!!
		return userRepository.findByUsername(username);
	}

	@Override	
	@Transactional
	public User save(User user) throws InvalidUsernameException, UsernameTakenException {
		if (user.getUsername() == null ||
			user.getUsername().isBlank() ||
			!user.getUsername().matches("[a-zA-Z0-9_-]+"))
			throw new InvalidUsernameException(user);
		Optional<User> storedUser = findByUsername(user.getUsername());
		if (storedUser.isPresent() && !storedUser.get().getId().equals(user.getId()))
			throw new UsernameTakenException(user, storedUser.get());
		//TODO ENCRYPT THE USER PASSWORD          pls :(
		return userRepository.save(user);
	}

	@Override
	public User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
		Optional<User> oldUser = findById(user.getId());
		if (oldUser.isEmpty())
			throw new UserNotFoundException(user);
		user.setPassword(oldUser.get().getPassword());
		return save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}
}
