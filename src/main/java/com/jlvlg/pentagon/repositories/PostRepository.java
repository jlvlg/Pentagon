package com.jlvlg.pentagon.repositories;

import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
/**
 * Defines methods to read and write to the Post database
 * @author Luann
 */
@Repository
public interface PostRepository extends JpaRepository <Post, Long> {
	Slice<Post> findByPageAndIsActiveTrue(Page page, Pageable pageable);
	long countByPageAndIsActiveTrue(Page page);
	Slice<Post> findByAuthorAndIsActiveTrue(User author, Pageable pageable);
	long countByAuthorAndIsActiveTrue(User author);
}
