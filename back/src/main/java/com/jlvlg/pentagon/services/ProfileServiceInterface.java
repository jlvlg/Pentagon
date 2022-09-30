package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidProfileNameException;
import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Defines Page specific repository access methods
 * @author Lucas
 *
 */
public interface ProfileServiceInterface extends GenericServiceInterface<Profile, Long>{
	/**
	 * Finds a page by user
	 *
	 * @param user the page's user
	 * @return The page
	 * @throws ProfileNotFoundException Page not found
	 */
	Profile findByUser(User user) throws ProfileNotFoundException;

	/**
	 * Saves a page to the database
	 * @param profile The page to be saved
	 * @throws InvalidProfileNameException A page name cannot be null, empty, or contain special characters
	 */
	@Override
	Profile save(Profile profile) throws InvalidProfileNameException;

	/**
	 * Updates a page in the database
	 * @param profile The paeg to be updated
	 * @throws ProfileNotFoundException Page not found
	 * @throws InvalidProfileNameException A page name cannot be null, empty, or contain special characters
	 */
	@Override
	Profile update(Profile profile) throws ProfileNotFoundException, InvalidProfileNameException;

	/**
	 * Permanently drops a page from the database
	 * @param profile The page to be deleted
	 * @throws ProfileNotFoundException Page not found
	 */
	@Override
	void delete(Profile profile) throws ProfileNotFoundException;

	@Override
	Profile findById(Long id) throws ProfileNotFoundException;

	List<Profile> search(String text);
}
