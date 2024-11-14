package com.daniela.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daniela.Entity.User;
import com.daniela.Security.TokenPayload;
import com.daniela.Security.TokenUtils;

import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtils extends TokenUtils {
    public TokenPayload decodeToken(String authorizationHeader) {
        DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(getSecret().getBytes()))
                .build()
                .verify(authorizationHeader.replace(getTokenPrefix(), ""));
        return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(User.Role.class));
    }
}