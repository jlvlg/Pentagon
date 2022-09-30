package com.jlvlg.pentagon.factories;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Profile;

import java.util.List;
import java.util.Locale;

/**
 * Contains a single static generate method that returns a Page object with random attribute values
 * @author Lucas
 *
 */
public class PageFactory {
	/**
	 * Generates a page
	 * @return A Page object with random attribute values
	 */
	public static Profile generate() {
		Faker faker = new Faker(new Locale("pt-BR"));
		return new Profile(
				List.of(
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate()
				),
				faker.hitchhikersGuideToTheGalaxy().location()
			);
	}
}
