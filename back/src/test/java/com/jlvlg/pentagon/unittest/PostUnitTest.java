package com.jlvlg.pentagon.unittest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jlvlg.pentagon.exceptions.PostVisibilityException;
import com.jlvlg.pentagon.factories.PostFactory;
import com.jlvlg.pentagon.factories.UserFactory;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;


/**
 * Post JUnit Test Class: Methods turnVisibleTo and turnInvisibleTo Tested
 * @author Luann
 */

public class PostUnitTest {
	private Post post;
	private User visibility;
	
	@BeforeEach
	public void setup() {
		post = PostFactory.generate();
		visibility = UserFactory.generate();
	}
	
	@Test
	@DisplayName("Allowing post visibility for a specific user")
	void turnVisibleTo() {
		assertDoesNotThrow(()-> post.turnVisibleTo(visibility));
		assertEquals(visibility, post.getVisibility().get(0));
	}
	
	@Test
	@DisplayName("Tried to turn post visible twice to the same user")
	void turnVisibleToAlreadyVisible() {
		assertDoesNotThrow(() -> post.turnVisibleTo(visibility));
		assertThrows(PostVisibilityException.class, () -> post.turnVisibleTo(visibility));
	}
	
	@Test
	@DisplayName("Disallowing post visibility for a specific user")
	void turnInvisibleTo() {
		assertDoesNotThrow(()-> post.turnVisibleTo(UserFactory.generate()));
		assertDoesNotThrow(()-> post.turnVisibleTo(visibility));
		assertEquals(visibility, post.getVisibility().get(1));
		assertDoesNotThrow(()-> post.turnInvisibleTo(visibility));
		assertFalse(()-> post.getVisibility().contains(visibility));
	}
	
	@Test
	@DisplayName("Tried to turn post invisible twice to the same user")
	void turnInvisibleToAlreadyInvisible() {
		assertDoesNotThrow(() -> post.turnVisibleTo(visibility));
		assertDoesNotThrow(() -> post.turnInvisibleTo(visibility));
		assertThrows(PostVisibilityException.class, () -> post.turnInvisibleTo(UserFactory.generate()));
	}
}
