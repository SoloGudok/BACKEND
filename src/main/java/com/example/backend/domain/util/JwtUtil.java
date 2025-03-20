package com.example.backend.domain.util;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}") // ✅ application.yml에서 secret 키 불러오기
    private String secretKey;

    @Value("${jwt.refresh-secret}") // ✅ refresh secret 키 불러오기
    private String refreshSecretKey;

    private final long ACCESS_EXPIRATION_TIME = 1000 * 60 * 15; // 15분
    private final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
//    private final long ACCESS_EXPIRATION_TIME = 1000 * 10; // 10초
//    private final long REFRESH_EXPIRATION_TIME = 1000 * 20; // 20초

    private Key getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(getSigningKey(refreshSecretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token, boolean isRefresh) {
        try {
            Key key = isRefresh ? getSigningKey(refreshSecretKey) : getSigningKey(secretKey);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

//            System.out.println("Refresh Token 검증 성공, 이메일: " + claims.getSubject()); // ✅ 로그 추가
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
//            System.out.println("토큰이 만료되었습니다.");
            return null;
        } catch (JwtException e) {
//            System.out.println("유효하지 않은 토큰: " + e.getMessage());
            return null;
        }
    }

}