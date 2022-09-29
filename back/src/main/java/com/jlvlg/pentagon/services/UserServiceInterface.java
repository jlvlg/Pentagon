package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.Auth;
import com.jlvlg.pentagon.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Defines User specific repository access methods
 * @author Lucas
 *
 */
public interface UserServiceInterface extends GenericServiceInterface<User, Long>{
	/**
	 * Finds a user by their username
	 *
	 * @param username the username to search for
	 * @return A user
	 */
	User findByUsername(String username) throws UserNotFoundException;

	/**
	 * Finds users by their username likeness
	 * @param username the username to search for
	 * @return A list of users
	 */
	List<User> findByUsernameLikeIgnoreCase(String username);

	/**
	 * Saves a user into the database
	 * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 */
	@Override
	User save(User user) throws InvalidUsernameException, UsernameTakenException;

	/**
	 * Updates a user in the database
	 * @throws InvalidUsernameException A username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 * @throws UserNotFoundException User not found
	 */
	@Override
	User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException;

	/**
	 * Permanently drops a user from the database
	 * @throws UserNotFoundException User not found
	 */
	@Override
	void delete(User user) throws UserNotFoundException;

	Auth loadUserByUsername(String username) throws UsernameNotFoundException;

	@Override
	User findById(Long id) throws UserNotFoundException;
}
