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
		moderator = new Moderator(UserFactory.generate());
	}

	@Test
	@DisplayName("Add moderator")
	void addModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}
	
	@Test
	@DisplayName("Add moderator when there's 5 moderators present")
	void addModeratorLimitExceeded() {
		Moderator moderators[] = new Moderator[5];
		for (int i = 0; i < 5; i++) {
			moderators[i] = new Moderator(UserFactory.generate());
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
	void addLeaderModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}

	@Test
	@DisplayName("Add leader moderator when there's one present")
	void addLeaderModeratorWhenLeaderModeratorPresent() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(LeaderModeratorPresentException.class, () -> page.addModerator(new Moderator(UserFactory.generate(), true)));
		assertEquals(moderator, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Add user twice as moderator")
	void addModeratorUserAlreadyModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(UserAlreadyModeratorException.class, () -> page.addModerator(new Moderator(moderator.getUser())));
	}
	
	@Test
	@DisplayName("Remove moderator")
	void removeModerator() {
		Moderator moderatorLeader = new Moderator(UserFactory.generate(), true);
		assertDoesNotThrow(() -> page.addModerator(moderatorLeader));
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertEquals(moderator, page.getModerators().get(1));
		assertDoesNotThrow(() -> page.removeModerator(moderator));
		assertFalse(page.getModerators().contains(moderator));
	}
	
	@Test
	@DisplayName("Remove leader moderator")
	void removeLeaderModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertDoesNotThrow(() -> page.addModerator(new Moderator(UserFactory.generate())));
		assertThrows(NoLeaderModeratorException.class, () -> page.removeModerator(moderator));
		assertEquals(moderator, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Remove last moderator of a page")
	void removeLastModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(ZeroModeratorsException.class, () -> page.removeModerator(moderator));
		assertEquals(moderator, page.getModerators().get(0));
	}
	
	@Test
	@DisplayName("Remove a moderator that has not been added previously")
	void removeModeratorNotFound() {
		assertDoesNotThrow(() -> page.addModerator(new Moderator(UserFactory.generate())));
		assertDoesNotThrow(() -> page.addModerator(new Moderator(UserFactory.generate())));
		assertThrows(ModeratorNotFoundException.class, () -> page.removeModerator(moderator));
	}
	
	@Test
	@DisplayName("Promote moderator")
	void promoteModerator() {
		Moderator moderator2 = new Moderator(UserFactory.generate());
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertDoesNotThrow(() -> page.addModerator(moderator2));
		assertEquals(moderator, page.getLeaderModerator().get());
		assertDoesNotThrow(() -> page.promoteModerator(moderator2));
		assertFalse(moderator.isLeader());
		assertEquals(moderator2, page.getLeaderModerator().get());
	}
	
	@Test
	@DisplayName("Promote moderator not previously added")
	void promoteModeratorNotFound() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertThrows(ModeratorNotFoundException.class, () -> page.promoteModerator(new Moderator(UserFactory.generate())));
	}
	
	@Test
	@DisplayName("Authenticate user as moderator")
	void authenticateUserAsModerator() {
		assertDoesNotThrow(() -> page.addModerator(moderator));
		assertTrue(page.authenticateUser(moderator.getUser()));
	}
	
}
