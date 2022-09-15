package com.jlvlg.pentagon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostableAndActiveTrue(Postable post);
	List<Comment> findByAuthorAndActiveTrue(User author);
}
