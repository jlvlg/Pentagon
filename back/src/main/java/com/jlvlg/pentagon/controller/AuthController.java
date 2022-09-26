package com.jlvlg.pentagon.controller;

import com.jlvlg.pentagon.exceptions.InvalidPageNameException;
import com.jlvlg.pentagon.exceptions.InvalidUsernameException;
import com.jlvlg.pentagon.exceptions.UsernameTakenException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.User;
import com.jlvlg.pentagon.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired private SecurityUtils securityUtils;
    @Autowired private Pentagon pentagon;

    @PostMapping("login")
    public ResponseEntity<String> login(User user) {
        try {
            return ResponseEntity.ok(securityUtils.authenticateUser(user));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).body("Access denied");
        }
    }

    @PostMapping("signup")
    public ResponseEntity<User> signUp(User user) throws InvalidPageNameException, InvalidUsernameException, UsernameTakenException {
        return ResponseEntity.ok(pentagon.saveUser(new User(user.getUsername(), user.getPassword())));
    }
}
