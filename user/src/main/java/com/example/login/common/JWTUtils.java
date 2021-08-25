package com.example.login.common;
import com.example.login.pojo.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {

    private static final long EXPIRE = 604800000L;

    private static final String SECRET = "zjq.168.com";

    private static final String TOKEN_PREFIX = "xdclass";

    private static final String SUBJECT = "zjq";

    public static String geneJsonWebToken(UserDTO userDTO) {

        String token = Jwts.builder()
                .setSubject(SUBJECT)
                .claim("email", userDTO.getUser().getEmail())
                .claim("id", userDTO.getUser().getUserId())
                .claim("name", userDTO.getUser().getName())
                .claim("role", userDTO.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)).signWith(SignatureAlgorithm.HS256, SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    public static Claims checkJWT(String token) {
        try {
            Claims claims = (Claims)Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
