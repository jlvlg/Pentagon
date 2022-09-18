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
	private User user;
	
	@BeforeEach
	void setUp() throws Exception {
		user = UserFactory.generate();
	}

	@Test
	@DisplayName("User's follow method")
	void testFollow() {
		assertDoesNotThrow(() -> user.follow(UserFactory.generate()));
	}
	
	@Test
	@DisplayName("You cannot follow an user twice")
	void testFollowAlreadyFollowed() {
		User user2 = UserFactory.generate();
		assertDoesNotThrow(() -> user.follow(user2));
		assertDoesNotThrow(() -> user.follow(UserFactory.generate()));
		assertThrows(UserAlreadyFollowedException.class, () -> user.follow(user2));
	}
	
	@Test
	@DisplayName("User's unffolow method")
	void testUnfollow() {
		User user2 = UserFactory.generate();
		assertDoesNotThrow(() -> user.follow(user2));
		assertDoesNotThrow(() -> user.unfollow(user2));
		assertThrows(UserNotFollowedException.class, () -> user.unfollow(UserFactory.generate()));
	}

}
