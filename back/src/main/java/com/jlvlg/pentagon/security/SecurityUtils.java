package com.jlvlg.pentagon.security;

import com.jlvlg.pentagon.exceptions.UserNotFoundException;
import com.jlvlg.pentagon.facade.Pentagon;
import com.jlvlg.pentagon.models.Auth;
import com.jlvlg.pentagon.settings.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.ArrayList;

@Service
public class SecurityUtils {
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private Pentagon pentagon;

    public String authenticateUser(Auth auth) throws AuthenticationException {
        Auth result = (Auth) authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                auth.getUsername(),
                auth.getPassword(),
                new ArrayList<>()
            )
        ).getPrincipal();
        return JwtUtils.generate(result.getUsername());
    }

    public boolean authorizeUser(String token, Long id) {
        try {
            return JwtUtils.extract(token.replace(AppSettings.JWT_HEADER_PREFIX, ""))
                    .equals(pentagon.findUser(id).getAuth().getUsername());
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
