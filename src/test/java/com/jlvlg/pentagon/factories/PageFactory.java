package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.ScoreMean;

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
	public static Page generate() {
		Faker faker = new Faker(new Locale("pt-BR"));
		return new Page(
				new ScoreMean[] {
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate(),
					ScoreMeanFactory.generate()
				},
				faker.hitchhikersGuideToTheGalaxy().location()
			);
	}
}
