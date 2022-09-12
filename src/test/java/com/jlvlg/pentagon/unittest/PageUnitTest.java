package com.jlvlg.pentagon.unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jlvlg.pentagon.exceptions.LeaderModeratorPresentException;
import com.jlvlg.pentagon.exceptions.ModeratorLimitExceededException;
import com.jlvlg.pentagon.exceptions.ModeratorNotFoundException;
import com.jlvlg.pentagon.exceptions.NoLeaderModeratorException;
import com.jlvlg.pentagon.exceptions.UserAlreadyModeratorException;
import com.jlvlg.pentagon.exceptions.ZeroModeratorsException;
import com.jlvlg.pentagon.factories.ModeratorFactory;
import com.jlvlg.pentagon.factories.PageFactory;
import com.jlvlg.pentagon.factories.UserFactory;
import com.jlvlg.pentagon.models.Moderator;
import com.jlvlg.pentagon.models.Page;

class PageUnitTest {
	private Page page;
	private Moderator moderator;

	@BeforeEach
	void setUp() throws Exception {
		page = PageFactory.generate();
		moderator = ModeratorFactory.generate();
	}

	@Test
	@DisplayName("Add moderator")
	void addModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}
	
	@Test
	@DisplayName("Add moderator when there's 5 moderators present")
	void addModeratorLimitExceededTest() {
		Moderator moderators[] = new Moderator[5];
		for (int i = 0; i < 5; i++) {
			moderators[i] = ModeratorFactory.generate();
		}
		assertDoesNotThrow(() -> page.addModerator(moderators[0]));
		assertDoesNotThrow(() -> page.addModerator(moderators[1]));
		assertDoesNotThrow(() -> page.addModerator(moderators[2]));
		assertDoesNotThrow(() -> page.addModerator(moderators[3]));
		assertDoesNotThrow(() -> page.addModerator(moderators[4]));
		assertThrows(ModeratorLimitExceededException.class, () -> page.addModerator(moderator));
		assertThrows(IndexOutOfBoundsException.class, () -> page.getModerators().get(6));
	}
	
	@Test
	@DisplayName("Add leader moderator")
	void addLeaderModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}

	@Test
	@DisplayName("Add leader moderator when there's one present")
	void addLeaderModeratorWhenLeaderModeratorPresentTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(LeaderModeratorPresentException.class, () -> page.addModerator(new Moderator(UserFactory.generate(), true)));
		assertEquals(moderator, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Add user twice as moderator")
	void addModeratorUserAlreadyModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(UserAlreadyModeratorException.class, () -> page.addModerator(new Moderator(moderator.getUser())));
	}
	
	@Test
	@DisplayName("Remove moderator")
	void removeModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(ModeratorFactory.generate()));
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(1));
		assertDoesNotThrow(() -> page.removeModerator(moderator));
		assertFalse(page.getModerators().contains(moderator));
	}
	
	@Test
	@DisplayName("Remove leader moderator")
	void removeLeaderModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertDoesNotThrow(() -> page.addModerator(ModeratorFactory.generate()));
		assertThrows(NoLeaderModeratorException.class, () -> page.removeModerator(moderator));
		assertEquals(moderator, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Remove last moderator of a page")
	void removeLastModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(ZeroModeratorsException.class, () -> page.removeModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}
	
	@Test
	@DisplayName("Remove a moderator that has not been added previously")
	void removeModeratorNotFoundTest() {
		assertDoesNotThrow(() -> page.addModerator(ModeratorFactory.generate()));
		assertDoesNotThrow(() -> page.addModerator(ModeratorFactory.generate()));
		assertThrows(ModeratorNotFoundException.class, () -> page.removeModerator(moderator));
	}
	
	@Test
	@DisplayName("Promote moderator")
	void promoteModeratorTest() {
		Moderator moderator2 = ModeratorFactory.generate();
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertDoesNotThrow(() -> page.addModerator(moderator2));
		assertEquals(moderator, page.getLeaderModerator().get());
		assertDoesNotThrow(() -> page.promoteModerator(moderator2));
		assertFalse(moderator.isLeader());
		assertEquals(moderator2, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Promote moderator not previously added")
	void promoteModeratorNotFoundTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(ModeratorNotFoundException.class, () -> page.promoteModerator(ModeratorFactory.generate()));
	}
	
	@Test
	@DisplayName("Authenticate user as moderator")
	void authenticateModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertTrue(page.authenticateUser(moderator.getUser()));
		assertFalse(page.authenticateUser(UserFactory.generate()));
	}
	
	@Test
	@DisplayName("Authenticate user as leader moderator")
	void authenticateLeaderTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertTrue(page.authenticateLeader(moderator.getUser()));
		assertFalse(page.authenticateLeader(UserFactory.generate()));
	}
	
	@Test
	@DisplayName("Get moderator by user returns correct moderator")
	void getModeratorByUserTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertDoesNotThrow(() -> page.addModerator(ModeratorFactory.generate()));
		assertEquals(moderator, page.getModeratorByUser(moderator.getUser()).get());
	}
	
	@Test
	@DisplayName("Get leader returns correct moderator")
	void getLeaderModeratorTest() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getLeaderModerator().get());
	}
}
