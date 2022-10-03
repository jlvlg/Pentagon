package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PostServiceInterface extends GenericServiceInterface<Post, Long>{
	List<Post> findByProfile(Profile profile);
	List<Post> findByAuthor(User author);
	List<Post> followingPosts(List<User> following);

	@Override
	Post save(Post object) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException;

	@Override
	Post update(Post object) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException;

	@Override
	void delete(Post object) throws PostNotFoundException;

	@Override
	Post findById(Long id) throws PostNotFoundException;
}
