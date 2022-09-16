package com.jlvlg.pentagon.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.PostRepository;

/*
 * implements logic busines before call the PostRepository methods
 * @autor Luann
 */

@Service
public class PostService implements PostServiceInterface {
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}
	@Override
	@Transactional
	public Post save(Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException{
		if(post.getTitle().isEmpty() || post.getTitle().isBlank()) {
			 throw new InvalidPostNameException(post);
		}
		if(post.getText().isEmpty() || post.getText().isBlank()) {
			throw new InvalidPostTextException(post);
		}
		if(post.getText().length() > 1500) {
			throw new PostMaxCharacterSizeExceededException(post);
		}
		return postRepository.save(post);
	}
	@Override
	public Post update(Post post) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException  {
		Optional<Post> oldPost = findById(post.getId());
		if(!oldPost.isPresent()) {
			throw new PostNotFoundException(post);
		}
		post.setCreationDate(oldPost.get().getCreationDate());
		return save(post);
	}
	@Override
	@Transactional
	public void delete(Post post) throws PostNotFoundException {
		if(findById(post.getId()).isEmpty()) {
			throw new PostNotFoundException(post);
		}
		postRepository.delete(post);
	}
	@Override
	public List<Post> findByTitleAndActiveTrue(String title) {
		return postRepository.findByTittleAndActiveTrue(title);
	}
	@Override
	public List<Post> findByTitleAndActiveTrue(User author) {
		return postRepository.findByAuthorAndActiveTrue(author);
	}
}
