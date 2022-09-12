package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.User;

/**
 * Contains a single static generate method that returns an User object with random attribute values
 * @author Lucas
 *
 */
public class UserFactory {
	/**
	 * Generates an user
	 * @return An User object with random attribute values
	 */
	public static User generate() {
		Faker faker = new Faker(new Locale("pt-BR"));
		return new User(
				faker.name().username(),
				faker.internet().password()
			);
	}
}
