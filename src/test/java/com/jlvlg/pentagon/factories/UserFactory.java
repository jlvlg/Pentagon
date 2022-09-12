package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.User;

/**
 * Contains a single static generate method that returns a User object with random attribute values
 * @author lucas
 *
 */
public class UserFactory {
	/**
	 * Generates a user
	 * @return A User object with random attribute values
	 */
	public static User generate() {
		Faker faker = new Faker(new Locale("pt-BR"));
		return new User(
				faker.internet().slug(),
				faker.internet().password()
			);
	}
}
