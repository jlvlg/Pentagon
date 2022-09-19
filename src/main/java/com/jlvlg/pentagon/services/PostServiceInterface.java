package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.models.Page;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;

public interface PostServiceInterface extends GenericServiceInterface<Post, Long>{
	List<Post> findByPageAndActiveTrue(Page page, Pageable pageable);
	List<Post> findByAuthorAndActiveTrue(User author, Pageable pageable);
}
