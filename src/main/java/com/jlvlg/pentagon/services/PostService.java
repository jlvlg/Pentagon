package com.jlvlg.pentagon.services;

import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.models.Page;
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
	@Autowired
	private PostRepository postRepository;

	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
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
		Optional<Post> oldPost = findById(post.getId());
		if(oldPost.isEmpty()) {
			throw new PostNotFoundException(post);
		}
		post.setCreationDate(oldPost.get().getCreationDate());
		return save(post);
	}

	@Transactional
	public void delete(Post post) throws PostNotFoundException {
		if(findById(post.getId()).isEmpty()) {
			throw new PostNotFoundException(post);
		}
		postRepository.delete(post);
	}

	public Slice<Post> findByPageAndIsActiveTrue(Page page, Pageable pageable) {
		return postRepository.findByPageAndIsActiveTrue(page, pageable);
	}

	public long countByPageAndIsActiveTrue(Page page) {
		return postRepository.countByPageAndIsActiveTrue(page);
	}

	public Slice<Post> findByAuthorAndIsActiveTrue(User author, Pageable pageable) {
		return postRepository.findByAuthorAndIsActiveTrue(author, pageable);
	}

	public long countByAuthorAndIsActiveTrue(User author) {
		return postRepository.countByAuthorAndIsActiveTrue(author);
	}
}
