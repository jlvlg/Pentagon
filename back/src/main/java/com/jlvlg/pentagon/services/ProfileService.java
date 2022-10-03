package com.jlvlg.pentagon.services;

import java.util.*;

import com.jlvlg.pentagon.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidProfileNameException;
import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.ProfileRepository;

@Service
public class ProfileService implements ProfileServiceInterface {
	@Autowired private ProfileRepository profileRepository;

	public Profile findById(Long id) throws ProfileNotFoundException {
		Optional<Profile> page = profileRepository.findById(id);
		if (page.isEmpty())
			throw new ProfileNotFoundException();
		return page.get();
	}

	public Profile findByUser(User user) throws ProfileNotFoundException {
		Optional<Profile> page = profileRepository.findByUser(user);
		if (page.isEmpty())
			throw new ProfileNotFoundException();
		return page.get();
	}

	public List<Profile> search(String text) {
		Set<Profile> profiles = new LinkedHashSet<>(profileRepository.findByNameContainsIgnoreCase(text));
		profiles.addAll(profileRepository.findByUser_Auth_UsernameContainsIgnoreCase(text));
		return new ArrayList<>(profiles);
	}

	public Profile save(Profile profile) throws InvalidProfileNameException {
		if (profile.getName() == null ||
			profile.getName().isBlank() ||
			!profile.getName().matches("[ 0-9A-Za-zÀ-ÖØ-öø-ÿ_.-]+"))
			throw new InvalidProfileNameException(profile);
		return profileRepository.save(profile);
	}

	public Profile update(Profile profile) throws ProfileNotFoundException, InvalidProfileNameException {
		profile.setUser(findById(profile.getId()).getUser());
		return save(profile);
	}

	public void delete(Profile profile) throws ProfileNotFoundException {
		findById(profile.getId());
		profileRepository.delete(profile);
	}
}
