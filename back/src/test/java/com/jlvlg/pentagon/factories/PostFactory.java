package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Post;

/**
 * Contains a single static generate method that returns a Post object with random attribute values
 * @author Lucas
 *
 */
public class PostFactory {
	/**
	 * Generates a post
	 * @return A Post object with random attribute values
	 */
	public static Post generate() {
		Faker faker = new Faker(new Locale("pt-BR"));
		return new Post(
				UserFactory.generate(),
				ProfileFactory.generate(),
				faker.lorem().paragraph(),
				faker.lorem().sentence()
			);
	}
}
