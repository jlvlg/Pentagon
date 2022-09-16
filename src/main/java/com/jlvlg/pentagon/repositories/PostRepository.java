package com.jlvlg.pentagon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
 
/**
 * Defines methods to read and write to the Post database
 * @author Luann
 */
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
	List<Post> findByTittleAndActiveTrue(String title);
	List<Post> findByAuthorAndActiveTrue(User author);
}
