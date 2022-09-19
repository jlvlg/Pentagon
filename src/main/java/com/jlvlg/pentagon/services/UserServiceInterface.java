package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Defines User specific repository access methods
 * @author Lucas
 *
 */
public interface UserServiceInterface extends GenericServiceInterface<User, Long>{
	Optional<User> findByUsername(String username);
	List<User> findByUsernameLikeIgnoreCase(String username);

	@Override
	User save(User user) throws InvalidUsernameException, UsernameTakenException;

	@Override
	User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException;

	@Override
	void delete(User user) throws UserNotFoundException;
}
