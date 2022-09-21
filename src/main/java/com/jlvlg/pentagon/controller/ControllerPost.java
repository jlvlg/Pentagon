package com.jlvlg.pentagon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
import com.jlvlg.pentagon.exceptions.OutOfPostsException;
import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
import com.jlvlg.pentagon.exceptions.PostNotFoundException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Post;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.repositories.PostRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pentagon/post")
public class ControllerPost {
	@Autowired
	private Pentagon pentagon;
	
	@PostMapping
	public ResponseEntity<Post> saveUser(@RequestBody Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException  {
		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.savePost(post));
	}
	@GetMapping("/{id}")
	public Post getOnePost(@PathVariable (value = "id") long id) throws PostNotFoundException{
		return pentagon.findPost(id);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Post> getOnePost(@PathVariable (value = "id") long id) throws PostNotFoundException{
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findPost(id));
//	}
	
	@GetMapping()
	public ResponseEntity<List<Post>> getAllPosts(User author, int numberPage) throws OutOfPostsException, UserNotFoundException{
		List<Post> allPosts = pentagon.loadPosts(author, numberPage);
		return ResponseEntity.status(HttpStatus.OK).body(allPosts);
	}
	
	@DeleteMapping
	public void deletebyId(@RequestBody Post post) throws PostNotFoundException {
		pentagon.deletePost(post);
	}
	
//	@GetMapping
//	public String aaaaaa() {
//		return "funciona caralho";
//	}
}
