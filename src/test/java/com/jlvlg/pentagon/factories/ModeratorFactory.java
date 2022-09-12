package com.jlvlg.pentagon.factories;

import com.jlvlg.pentagon.models.Moderator;

/**
 * Contains a single static generate method that returns a Moderator object with random attribute values
 * @author Lucas
 *
 */
public class ModeratorFactory {
	/**
	 * Generates a moderator
	 * @return A Moderator object with random attribute values
	 */
	public static Moderator generate() {
		return new Moderator(
				UserFactory.generate()
			);
	}
}
