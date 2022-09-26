package com.jlvlg.pentagon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jlvlg.pentagon.settings.AppSettings;

public class JwtUtils {
    public static String generate(String subject) {
        return JWT.create()
                .withSubject(subject)
                .sign(Algorithm.HMAC512(AppSettings.JWT_SECRET.getBytes()));
    }

    public static String extract(String token) {
        return JWT.require(Algorithm.HMAC512(AppSettings.JWT_SECRET.getBytes()))
                .build().verify(token).getSubject();
    }
}
