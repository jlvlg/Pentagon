/**
 * 
 */
package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Profile;

/**
 * Thrown when trying to access a non-existing page
 * @author Lucas
 *
 */
public class ProfileNotFoundException extends ObjectNotFoundException {
	private Profile profile;
	
	public ProfileNotFoundException() {
		super("Page");
	}
	
	public ProfileNotFoundException(Profile profile) {
		this();
		this.profile = profile;
	}

	public Profile getPage() {
		return profile;
	}
}
