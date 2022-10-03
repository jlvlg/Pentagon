package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.Auth;
import com.jlvlg.pentagon.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserServiceInterface extends GenericServiceInterface<User, Long>{

	User findByUsername(String username) throws UserNotFoundException;

	@Override
	User save(User user) throws InvalidUsernameException, UsernameTakenException;

	@Override
	User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException;

	@Override
	void delete(User user) throws UserNotFoundException;

	Auth loadUserByUsername(String username) throws UsernameNotFoundException;

	@Override
	User findById(Long id) throws UserNotFoundException;
}
