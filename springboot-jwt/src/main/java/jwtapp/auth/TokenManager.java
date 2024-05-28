package jwtapp.auth;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;


@Service
public class TokenManager {
    private static final int validityTime = 5 * 60 * 1000; //validity time for setexpirtaion
    private static final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis()))
                .issuer("www.salih.com")
                .issuedAt(new Date(System.currentTimeMillis() + validityTime))
                .signWith(key)
                .compact();
    }

    public boolean tokenValidate(String jwt) {

        if (getUsernameToken(jwt) != null && isExpired(jwt)) {
            return true;
        }
        return false;
    }

    public String getUsernameToken(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject(); // return username

    }

    public boolean isExpired(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(new Date(System.currentTimeMillis())); // su anın ilerisinde mi?
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
    }
}
