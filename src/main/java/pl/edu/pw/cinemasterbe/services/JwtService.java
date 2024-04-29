package pl.edu.pw.cinemasterbe.services;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private static final SecretKey key = Jwts.SIG.HS512.key().build();
    private static final int JWT_EXPIRE_TIME = 6 * 60 * 60 * 1000;
    private static final String JWT_ISSUER = "Cinemaster";

    public String generateJwt(String username) {
        var currentDate = new Date();
        var expireDate = new Date(currentDate.getTime() + JWT_EXPIRE_TIME);

        return Jwts.builder()
                .issuedAt(currentDate)
                .expiration(expireDate)
                .issuer(JWT_ISSUER)
                .subject(username)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwt(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
