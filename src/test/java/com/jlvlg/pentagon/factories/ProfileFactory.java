package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Profile;

/*
* Contains a single static generate method that returns a Profile object with random attribute values
* @author Luann
*
*/

public class ProfileFactory {
	/**
	 * Generates a Profile
	 * @return A Profile object with random attribute values
	 */
	public static Profile generate() {
		Faker faker = new Faker(new Locale("pt-br"));
		PageFactory page = new PageFactory();
		
		return new Profile(
				faker.name().username()
				);
				
	}
}

