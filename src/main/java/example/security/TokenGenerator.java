package example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${jwt.secret}")
    String secretWord;

    @Value("${jwt.expiration}")
    int expirationTime;

    public String generateToken (Authentication authentication) {
        String username = authentication.getName();
        Date current = new Date();
        Date expiration = new Date(current.getTime() + expirationTime);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretWord)
                .compact();
        return token;
    }

    public String getUsernameFromToken (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretWord).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token is expired or incorrect");
        }
    }
}
