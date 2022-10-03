package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines methods to read and write to the Post database
 * @author Luann
 */
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
	List<Post> findByProfileOrderByCreationDateDesc(Profile profile);
	List<Post> findByAuthorOrderByCreationDateDesc(User author);
	List<Post> findByAuthorInOrderByCreationDateDesc(List<User> authors);
}
