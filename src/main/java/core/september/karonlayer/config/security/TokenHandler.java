package core.september.karonlayer.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import core.september.karonlayer.config.Config;
import core.september.karonlayer.persistence.model.User;


public final class TokenHandler {

    private final String secret;
    private final UserService userService;

    public TokenHandler(UserService userService) {
        this.secret = Config.secret;
        this.userService = userService;
    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(
                		TimeUnit.MINUTES.toMillis(5)+System.currentTimeMillis()
                		))
                .compact();
    }
    
    public String createRenewTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}

