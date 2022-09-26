//package com.jlvlg.pentagon.controller;
//
//import java.util.List;
//import java.util.Optional;
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
//import com.jlvlg.pentagon.exceptions.PageNotFoundException;
//import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
//import com.jlvlg.pentagon.facade.Pentagon;
//import com.jlvlg.pentagon.models.Score;
//import com.jlvlg.pentagon.models.User;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping("/pentagon/user/score")
//public class ControllerScore {
//	@Autowired
//	private Pentagon pentagon;
//
//
//	@PostMapping
//	public ResponseEntity<Score> save(@RequestBody Score score) throws ScoreOutOfAllowedException {
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pentagon.saveScore(score));
//	}
//
//	@GetMapping("/search/{user}/{category}")
//	public ResponseEntity<Optional<Score>> find(@PathVariable (value = "user") User user, @PathVariable (value = "category") String category, User author) {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.findScore(user, category, author));
//
//	}
//
//	@GetMapping("/search/{user}/{category}")
//	public ResponseEntity<List<Score>> load (@PathVariable (value = "user") User user,  @PathVariable (value = "category") String category) {
//		List<Score> score = pentagon.loadScores(user, category);
//		return ResponseEntity.status(HttpStatus.OK).body(score);
//	}
//
//	@DeleteMapping("/delete/{score}")
//	public void delete(@PathVariable Score score) throws Exception {
//		pentagon.deleteScore(score);
//	}
//
//	@PutMapping("/update/{score}")
//	public ResponseEntity<Score> update (@PathVariable (value = "score") Score score) throws ScoreOutOfAllowedException {
//		return ResponseEntity.status(HttpStatus.OK).body(pentagon.updateScore(score));
//	}
//
//	@PutMapping("/vote/{user}/{category}")
//	public void vote(@PathVariable (value = "user") User user, @PathVariable (value = "category") String category, User author, int score) throws PageNotFoundException, ScoreOutOfAllowedException {
//		pentagon.vote(user, category, author, score);
//	}
// }
