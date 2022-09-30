package com.jlvlg.pentagon.exceptions;

import com.jlvlg.pentagon.models.Profile;

/**
 * Thrown when trying to create a page with an invalid name
 * @author Lucas
 *
 */
public class InvalidProfileNameException extends Exception {
	private final Profile profile;
	
	public InvalidProfileNameException(Profile profile) {
		super("A page name cannot be null, empty, or contain special characters");
		this.profile = profile;
	}

	public Profile getPage() {
		return profile;
	}
}
