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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

	public List<Post> followingPosts(List<User> following) {
		return postRepository.findByAuthorInOrderByCreationDateDesc(following);
	}

	public List<Post> findByProfile(Profile profile) {
		return postRepository.findByProfileOrderByCreationDateDesc(profile);
	}

	public List<Post> findByAuthor(User author) {
		return postRepository.findByAuthorOrderByCreationDateDesc(author);
	}
}
