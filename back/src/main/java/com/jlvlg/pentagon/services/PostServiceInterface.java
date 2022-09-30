package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostServiceInterface extends GenericServiceInterface<Post, Long>{
	Slice<Post> findByProfileAndIsActiveTrue(Profile profile, Pageable pageable);
	long countByProfileAndIsActiveTrue(Profile profile);
	Slice<Post> findByAuthorAndIsActiveTrue(User author, Pageable pageable);
	long countByAuthorAndIsActiveTrue(User author);

	@Override
	Post save(Post object) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException;

	@Override
	Post update(Post object) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException;

	@Override
	void delete(Post object) throws PostNotFoundException;

	@Override
	Post findById(Long id) throws PostNotFoundException;
}
