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

	/**
	 * Finds a user by their username
	 * @param username the username to search for
	 * @return An optional that might contain a user
	 */
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Finds users by username likeness
	 * @param username the username to search for
	 * @return A list of users
	 */
	public List<User> findByUsernameLikeIgnoreCase(String username) {
		return userRepository.findByUsernameLikeIgnoreCase(username);
	}

	/**
	 * Saves a user into the database
	 * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 */
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

	/**
	 * Updates a user in the database
	 * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 * @throws UserNotFoundException User not found
	 */
	public User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
		Optional<User> oldUser = findById(user.getId());
		if (oldUser.isEmpty())
			throw new UserNotFoundException(user);
		user.setPassword(oldUser.get().getPassword());
		return save(user);
	}

	/**
	 * Permanently drops a user from the database
	 * @throws UserNotFoundException User not found
	 */
	public void delete(User user) throws UserNotFoundException {
		if (findById(user.getId()).isEmpty())
			throw new UserNotFoundException(user);
		userRepository.delete(user);
	}
}
