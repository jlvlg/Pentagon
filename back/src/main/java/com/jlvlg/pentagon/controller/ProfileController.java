package com.jlvlg.pentagon.controller;

import com.jlvlg.pentagon.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jlvlg.pentagon.exceptions.InvalidProfileNameException;
import com.jlvlg.pentagon.exceptions.ProfileNotFoundException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Profile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("profiles")
public class ProfileController {
	@Autowired private Pentagon pentagon;
    @Autowired private SecurityUtils securityUtils;

	@PostMapping
	public ResponseEntity<Profile> save(@RequestBody Profile profile) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pentagon.saveProfile(profile));
        } catch (InvalidProfileNameException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

	@GetMapping
	public ResponseEntity<Profile> getProfile(Optional<Long> id, Optional<String> username) {
        try {
            if (id.isPresent()) {
                return ResponseEntity.ok(pentagon.findProfile(id.get()));
            }
            if (username.isPresent()) {
                return ResponseEntity.ok(pentagon.findProfile(username.get()));
            }
            return ResponseEntity.badRequest().build();
        } catch (ProfileNotFoundException | UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("search")
    public ResponseEntity<List<Profile>> search(String text) {
        try {
            return ResponseEntity.ok(pentagon.searchProfiles(text));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<Profile> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                          @RequestBody Profile profile) {
        try {
            if (securityUtils.authorizeUser(token, pentagon.findProfile(profile.getId()).getUser().getId())) {
                return ResponseEntity.ok(pentagon.updateProfile(profile));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidProfileNameException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                       @RequestBody Profile profile) {
        try {
            if (securityUtils.authorizeUser(token, pentagon.findProfile(profile.getId()).getUser().getId())) {
                pentagon.deleteProfile(profile);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
