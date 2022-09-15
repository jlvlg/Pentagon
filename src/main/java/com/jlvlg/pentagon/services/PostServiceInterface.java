package com.jlvlg.pentagon.services;

import java.util.List;

import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;

public interface PostServiceInterface extends GenericServiceInterface<Post, Long>{
	List<Post> findByTitleAndActiveTrue(String title);
	List<Post> findByTitleAndActiveTrue(User author);
}
