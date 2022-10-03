package com.jlvlg.pentagon.controller;

import java.util.List;
import java.util.Optional;

import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.exceptions.ScoreNotFoundException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlvlg.pentagon.exceptions.ScoreOutOfAllowedException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Score;
import com.jlvlg.pentagon.models.User;

@RestController
@RequestMapping("score")
public class ScoreController {
	@Autowired
	private Pentagon pentagon;

	@PostMapping
	public ResponseEntity<Score> save(Long profile, String category, Long author, int score) {
        try {
            return ResponseEntity.ok(pentagon.vote(profile, category, author, score));
        } catch (ScoreOutOfAllowedException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (UserNotFoundException | ProfileNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<Optional<Score>> find(Long profile, String category, Long author) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(pentagon.findScore(pentagon.findProfile(profile), category, pentagon.findUser(author)));
		} catch (ProfileNotFoundException | UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("profile")
	public ResponseEntity<List<Score>> load(Long profile, Long author) {
		try {
			return ResponseEntity.ok(pentagon.loadScores(pentagon.findProfile(profile), pentagon.findUser(author)));
		} catch (ProfileNotFoundException | UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody Score score) {
		try {
			pentagon.deleteScore(score);
			return ResponseEntity.noContent().build();
		} catch (ScoreNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
 }
