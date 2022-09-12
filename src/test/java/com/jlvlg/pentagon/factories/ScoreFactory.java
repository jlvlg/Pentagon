package com.jlvlg.pentagon.factories;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Score;

/*
* Contains a single static generate method that returns a Score object with random attribute values
* @author Luann
*
*/

public class ScoreFactory {
	/**
	 * Generates a Score
	 * @return A Score object with random attribute values
	 */
	
	
	public static Score generate() {
		Faker faker = new Faker(new Locale("pt-br"));
		
		return new Score(
				faker.number().numberBetween(-5, 5), UserFactory.generate(), 
				(Profile) ProfileFactory.generate(), faker.name().name());
	}
}
