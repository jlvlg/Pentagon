//package com.jlvlg.pentagon.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jlvlg.pentagon.exceptions.InvalidPostNameException;
//import com.jlvlg.pentagon.exceptions.InvalidPostTextException;
//import com.jlvlg.pentagon.exceptions.OutOfPostsException;
//import com.jlvlg.pentagon.exceptions.PostMaxCharacterSizeExceededException;
//import com.jlvlg.pentagon.exceptions.PostNotFoundException;
//import com.jlvlg.pentagon.exceptions.PostVisibilityException;
//import com.jlvlg.pentagon.exceptions.UserNotFoundException;
//import com.jlvlg.pentagon.facade.Pentagon;
//import com.jlvlg.pentagon.models.Post;
//import com.jlvlg.pentagon.models.User;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/pentagon/post")
//public class ControllerPost {
//	@Autowired
//	private Pentagon pentagon;
//
//	@PostMapping()
//	public ResponseEntity<Post> save(@RequestBody Post post) throws InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException  {
//		return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.savePost(post));
//	}
//
////	@GetMapping("/{id}")
////	public Post getOnePost(@PathVariable (value = "id") long id) throws PostNotFoundException{
////		return pentagon.findPost(id);
////	}
//
//	@GetMapping("/search/{id}")
//	public ResponseEntity<Post> load(@PathVariable (value = "id") long id) throws PostNotFoundException{
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findPost(id));
//	}
//
//	@GetMapping("/search/{author}")
//	public ResponseEntity<List<Post>> loadAll(User author, int numberPage) throws OutOfPostsException, UserNotFoundException{
//		List<Post> allPosts = pentagon.loadPosts(author, numberPage);
//		return ResponseEntity.status(HttpStatus.OK).body(allPosts);
//	}
//
//	@DeleteMapping("/delete/{post}")
//	public void delete(@PathVariable (value = "post") Post post) throws PostNotFoundException {
//		pentagon.deletePost(post);
//	}
//
//	@PutMapping("/update/{post}")
//	public ResponseEntity<Post> update(@PathVariable (value = "post")Post post) throws PostNotFoundException, InvalidPostNameException, InvalidPostTextException, PostMaxCharacterSizeExceededException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.updatePost(post));
//	}
//
//	@PutMapping
//	public void switchPostIsActive(Post post) throws PostNotFoundException, PostMaxCharacterSizeExceededException, InvalidPostNameException, InvalidPostTextException {
//		pentagon.switchPostIsActive(post);
//	}
//
//	@PutMapping
//	public void like (Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
//		pentagon.likePost(post);
//	}
//
//	@PutMapping
//	public void unlike (Post post) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
//		pentagon.unlikePost(post);
//	}
//
//	@PutMapping("/editing/{post}")
//	public void edit (@PathVariable (value = "post") Post post, String image, String title, String text) throws PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
//		pentagon.editPost(post, image, title, text);
//	}
//
//
//	@PutMapping("/{user}/{post}")
//	public ResponseEntity<Boolean> turnPostVisibleTo(@PathVariable (value = "user") User user, @PathVariable (value = "post") Post post) throws PostVisibilityException, PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.turnPostInvisibleTo(user, post));
//	}
//
//	@PutMapping("/{user}/{post}")
//	public ResponseEntity<Boolean> turnPostInvisibleTo(@PathVariable (value = "user") User user, @PathVariable (value = "post") Post post) throws PostVisibilityException, PostMaxCharacterSizeExceededException, PostNotFoundException, InvalidPostNameException, InvalidPostTextException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.turnPostInvisibleTo(user, post));
//	}
//
//
////	@GetMapping
////	public String aaaaaa() {
////		return "funciona caralho";
////	}
//}
