package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Comment;

/*
* Contains a single static generate method that returns a Comment object with random attribute values
* @author Luann
*
*/
public class CommentFactory {
	/**
	 * Generates a Comment
	 * @return A Comment object with random attribute values
	 */
	
	public static Comment generate() {
		Faker faker = new Faker(new Locale("pt-br"));
		UserFactory author = new UserFactory();
		PostFactory post = new PostFactory();
		
		return new Comment(
				author.generate(), faker.lorem().paragraph(), post.generate()
				);
	}
}
