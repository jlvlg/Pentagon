/**
 * 
 */
package com.jlvlg.pentagon.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jlvlg.pentagon.exceptions.UserAlreadyFollowedException;
import com.jlvlg.pentagon.exceptions.UserNotFollowedException;
import com.jlvlg.pentagon.factories.UserFactory;
import com.jlvlg.pentagon.models.User;

class UserUnitTest {
	private User user1;
	private User user2;
	
	@BeforeEach
	void setUp() {
		user1 = UserFactory.generate();
		user2 = UserFactory.generate();
	}

	@Test
	@DisplayName("User's follow method")
	void testFollow() {
		assertDoesNotThrow(() -> user1.follow(user2));
		assertTrue(user1.getFollowing().contains(user2));
	}
	
	@Test
	@DisplayName("You cannot follow a user twice")
	void testFollowAlreadyFollowed() {
		assertDoesNotThrow(() -> user1.follow(user2));
		assertThrows(UserAlreadyFollowedException.class, () -> user1.follow(user2));
		assertEquals(1, user1.getFollowing().size());
	}
	
	@Test
	@DisplayName("User's unfollow method")
	void testUnfollow() {
		assertDoesNotThrow(() -> user1.follow(user2));
		assertDoesNotThrow(() -> user1.unfollow(user2));
		assertFalse(user2.getFollowing().contains(user2));
	}

	@Test
	@DisplayName("You cannot unfollow a user you're not already following")
	void testUnfollowNotFollowed() {
		assertDoesNotThrow(() -> user1.follow(user2));
		assertDoesNotThrow(() -> user1.unfollow(user2));
		assertThrows(UserNotFollowedException.class, () -> user1.unfollow(user2));
	}
}
