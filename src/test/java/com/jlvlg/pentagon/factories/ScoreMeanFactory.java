package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.ScoreMean;

/*
* Contains a single static generate method that returns a ScoreMean object with random attribute values
* @author Luann
*
*/
public class ScoreMeanFactory {
	/**
	 * Generates a ScoreMean
	 * @return A ScoreMean object with random attribute values
	 */
	public static ScoreMean generate() {
		Faker faker = new Faker(new Locale ("pt-br"));
		return new ScoreMean (
				faker.name().name(),
				(float) faker.number().numberBetween(-5, 5)
				);
	}
}
