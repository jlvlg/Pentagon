package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.models.User;

public interface UserServiceInterface extends GenericServiceInterface<User, Long>{
	User findByUsername(String username);
}
