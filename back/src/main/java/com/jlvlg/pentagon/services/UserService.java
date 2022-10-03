/**
 * 
 */
package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import com.jlvlg.pentagon.models.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {
	@Autowired private UserRepository userRepository;

	public User findById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException();
		return user.get();
	}

	public User findByUsername(String username) throws UserNotFoundException {
		Optional<User> user = userRepository.findByAuth_Username(username);
		if (user.isEmpty())
			throw new UserNotFoundException();
		return user.get();
	}

	public User save(User user) throws InvalidUsernameException, UsernameTakenException {
		if (user.getAuth().getUsername() == null ||
			user.getAuth().getUsername().isBlank() ||
			!user.getAuth().getUsername().matches("[a-zA-Z0-9_.-]+"))
			throw new InvalidUsernameException(user);
		Optional<User> storedUser = userRepository.findByAuth_Username(user.getAuth().getUsername());
		if (storedUser.isPresent() && !storedUser.get().getId().equals(user.getId()))
			throw new UsernameTakenException(user, storedUser.get());
		return userRepository.save(user);
	}

	public User update(User user) throws UsernameTakenException, InvalidUsernameException, UserNotFoundException {
		User oldUser = findById(user.getId());
		user.getAuth().setPassword(oldUser.getAuth().getPassword());
		user.setJoinDate(oldUser.getJoinDate());
		return save(user);
	}

	public void delete(User user) throws UserNotFoundException {
		findById(user.getId());
		userRepository.delete(user);
	}

	@Override
	public Auth loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = findByUsername(username);
			return user.getAuth();
		} catch (UserNotFoundException e) {
			throw new UsernameNotFoundException("User not found");
		}
	}
}
