package com.jlvlg.pentagon.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
 
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
	List<Post> findByTittleAndActiveTrue(String title);
	List<Post> findByAuthorAndActiveTrue(User author);
}
