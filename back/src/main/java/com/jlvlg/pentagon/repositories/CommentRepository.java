package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Comment;
import com.jlvlg.pentagon.models.Postable;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines methods to read and write to the Comment database
 * @author Luann
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostable(Postable postable);
	List<Comment> findByAuthor(User author);
}
