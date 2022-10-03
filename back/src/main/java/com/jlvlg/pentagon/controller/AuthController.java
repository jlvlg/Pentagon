package com.jlvlg.pentagon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jlvlg.pentagon.exceptions.*;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Auth;
import com.jlvlg.pentagon.models.Profile;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.security.SecurityUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired private Pentagon pentagon;
    @Autowired private SecurityUtils securityUtils;

    @PostMapping("login")
    public ResponseEntity<Profile> login(@RequestBody Auth auth) {
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, securityUtils.authenticateUser(auth))
                    .body(pentagon.findProfile(auth.getUsername()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (UserNotFoundException | ProfileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("signup")
    public ResponseEntity<Profile> signUp(@RequestBody Auth auth) {
        try {
            Profile profile = pentagon.saveUser(new User(auth.getUsername(), auth.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(HttpHeaders.AUTHORIZATION, securityUtils.authenticateUser(auth))
                    .body(profile);
        } catch (InvalidUsernameException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (UsernameTakenException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (InvalidProfileNameException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping
    public ResponseEntity<Profile> updateAuth(@RequestBody List<Auth> body) {
        try {
            return ResponseEntity.ok(pentagon.updateAuth(body.get(0), body.get(1)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidUsernameException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (IncorrectPasswordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (UsernameTakenException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
