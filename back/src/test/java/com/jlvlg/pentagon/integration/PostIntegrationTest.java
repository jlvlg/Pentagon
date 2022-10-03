/**
 * 
 */
package com.jlvlg.pentagon.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jlvlg.pentagon.controller.PostController;
import com.jlvlg.pentagon.controller.ProfileController;
import com.jlvlg.pentagon.controller.UserController;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.factories.UserFactory;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.User;


@SpringBootTest
class PostIntegrationTest {
	@Autowired PostController postController;
	@Autowired UserController userController;
	@Autowired ProfileController profileController;
	User author;
	Profile profile;
	Post post;
	
	@BeforeEach
	void setUp() throws Exception {
		author = UserFactory.generate();
		profile = new Profile(author);
		post = new Post(author, profile, "random title", "random text");
		profileController.save(profile);
	}
	
	@AfterEach
	void tearDown() throws PostNotFoundException, ProfileNotFoundException {
		postController.delete(post);
		profileController.delete(profile);
	}

	@Test
	void store() {
		assertDoesNotThrow(() -> postController.save(post));
		Post foundPost = postController.getPosts(Optional.of(post.getId()), Optional.of(post.getId())).getBody().get(0);
		assertEquals(post.getTitle(), foundPost.getTitle());
		assertEquals(post.getText(), foundPost.getText());
	}
}
