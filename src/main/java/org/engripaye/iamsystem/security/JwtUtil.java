package org.engripaye.iamsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.engripaye.iamsystem.model.Role;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    String secret = "your-very-secret-key-that-is-at-least-32-bytes-long";
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

   public String generateToken(String username, Role role){
       return Jwts.builder()
               .setSubject(username)
               .claim("role", role.name())
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 86400000))
               .signWith(key, SignatureAlgorithm.HS256) // ✅ new correct usage
               .compact();
    }

    public String getUsername(String token){
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return jws.getBody().getSubject(); // ✅ Fixed: added return statement

    }

    // ✅ Add this method
    public String getRole(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return jws.getBody().get("role", String.class); // Cast "role" claim to String
    }
}
