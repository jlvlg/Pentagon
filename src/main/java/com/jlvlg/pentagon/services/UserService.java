/**
 * 
 */
package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

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

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findByUsernameLikeIgnoreCase(String username) {
		return userRepository.findByUsernameLikeIgnoreCase(username);
	}

	public User save(User user) throws InvalidUsernameException, UsernameTakenException {
		if (user.getUsername() == null ||
			user.getUsername().isBlank() ||
			!user.getUsername().matches("[a-zA-Z0-9_-]+"))
			throw new InvalidUsernameException(user);
		Optional<User> storedUser = findByUsername(user.getUsername());
		if (storedUser.isPresent() && !storedUser.get().getId().equals(user.getId()))
			throw new UsernameTakenException(user, storedUser.get());
		return userRepository.save(user);
	}

	public User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
		Optional<User> oldUser = findById(user.getId());
		if (oldUser.isEmpty())
			throw new UserNotFoundException(user);
		user.setPassword(oldUser.get().getPassword());
		return save(user);
	}

	public void delete(User user) throws UserNotFoundException {
		if (findById(user.getId()).isEmpty())
			throw new UserNotFoundException(user);
		userRepository.delete(user);
	}
}
