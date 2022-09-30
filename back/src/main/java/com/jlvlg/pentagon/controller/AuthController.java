package com.jlvlg.pentagon.controller;

import com.jlvlg.pentagon.exceptions.InvalidProfileNameException;
import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Auth;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired private SecurityUtils securityUtils;
    @Autowired private Pentagon pentagon;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody Auth auth) {
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, securityUtils.authenticateUser(auth))
                    .body(pentagon.findUser(auth.getUsername()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@RequestBody Auth auth) {
        try {
            pentagon.saveUser(new User(auth.getUsername(), auth.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(HttpHeaders.AUTHORIZATION, securityUtils.authenticateUser(auth))
                    .body(pentagon.findUser(auth.getUsername()));
        } catch (InvalidUsernameException e) {
            return ResponseEntity.unprocessableEntity().build();
        } catch (UsernameTakenException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (InvalidProfileNameException | UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
