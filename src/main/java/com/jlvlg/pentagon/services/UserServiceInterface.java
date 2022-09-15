package com.jlvlg.pentagon.services;

import java.util.Optional;

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.models.User;

/**
 * Defines User specific repository access methods
 * @author Lucas
 *
 */
public interface UserServiceInterface extends GenericServiceInterface<User, Long>{
	Optional<User> findByUsername(String username);

	/**
	 * Saves an user into the database
	 * @throws InvalidUsernameException An username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 */
	@Override
	User save(User user) throws InvalidUsernameException, UsernameTakenException;

	/**
	 * Updates an user in the database
	 * @throws InvalidUsernameException An username name cannot be null, empty, or contain spaces and/or special characters
	 * @throws UsernameTakenException Two users cannot have the same username
	 * @throws UserNotFoundException User not found
	 */
	@Override
	User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException;

	/**
	 * Permanently drops an user from the database
	 */
	@Override
	void delete(User user);
	
}
