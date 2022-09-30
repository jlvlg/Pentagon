package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/*
 * implements logic busines before call the PostRepository methods
 * @autor Luann
 */

@Service
public class PostService implements PostServiceInterface {
	@Autowired private PostRepository postRepository;

	public Post findById(Long id) throws PostNotFoundException {
		Optional<Post> post = postRepository.findById(id);
		if (post.isEmpty())
			throw new PostNotFoundException();
		return post.get();
	}

	@Transactional
	public Post save(Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException{
		if(post.getTitle() == null || post.getTitle().isBlank()) {
			 throw new InvalidPostNameException(post);
		}
		if(post.getText() == null || post.getText().isBlank()) {
			throw new InvalidPostTextException(post);
		}
		if(post.getText().length() > 1500) {
			throw new PostMaxCharacterSizeExceededException(post);
		}
		return postRepository.save(post);
	}

	public Post update(Post post) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException  {
		Post oldPost = findById(post.getId());
		post.setCreationDate(oldPost.getCreationDate());
		return save(post);
	}

	@Transactional
	public void delete(Post post) throws PostNotFoundException {
		findById(post.getId());
		postRepository.delete(post);
	}

	public Slice<Post> findByProfileAndIsActiveTrue(Profile profile, Pageable pageable) {
		return postRepository.findByProfileAndActiveTrue(profile, pageable);
	}

	public long countByProfileAndIsActiveTrue(Profile profile) {
		return postRepository.countByProfileAndActiveTrue(profile);
	}

	public Slice<Post> findByAuthorAndIsActiveTrue(User author, Pageable pageable) {
		return postRepository.findByAuthorAndActiveTrue(author, pageable);
	}

	public long countByAuthorAndIsActiveTrue(User author) {
		return postRepository.countByAuthorAndActiveTrue(author);
	}
}
