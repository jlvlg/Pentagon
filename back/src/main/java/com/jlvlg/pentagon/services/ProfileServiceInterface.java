package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidProfileNameException;
import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.User;

import java.util.List;
import java.util.Optional;

public interface ProfileServiceInterface extends GenericServiceInterface<Profile, Long>{
	Profile findByUser(User user) throws ProfileNotFoundException;

	@Override
	Profile save(Profile profile) throws InvalidProfileNameException;

	@Override
	Profile update(Profile profile) throws ProfileNotFoundException, InvalidProfileNameException;

	@Override
	void delete(Profile profile) throws ProfileNotFoundException;

	@Override
	Profile findById(Long id) throws ProfileNotFoundException;

	List<Profile> search(String text);
}
