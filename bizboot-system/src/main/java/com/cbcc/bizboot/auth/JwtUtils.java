package com.cbcc.bizboot.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final JwtProperties jwtProperties;

    private final SecretKey accessKey;

    private final SecretKey refreshKey;

    public enum TokenType {
        AccessToken,
        RefreshToken
    }

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.accessKey = Keys.hmacShaKeyFor(jwtProperties.getAccessToken().getSecretKey().getBytes());
        this.refreshKey = Keys.hmacShaKeyFor(jwtProperties.getRefreshToken().getSecretKey().getBytes());
    }

    /**
     * 生成 accessToken
     */
    public String generateAccessToken(String username) {
        long duration = jwtProperties.getAccessToken().getDuration();
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * duration);
        return generateAccessToken(username, expiration);
    }

    /**
     * 生成 accessToken
     */
    public String generateAccessToken(String username, Date expiration) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiration)
                .signWith(accessKey)
                .compact();
    }

    /**
     * 验证 accessToken
     */
    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parser().verifyWith(accessKey).build().parseSignedClaims(accessToken);
            return true;
        } catch (JwtException e) {
            return false;  // token 不合法或已过期
        }
    }

    /**
     * 生成 refreshToken
     */
    public String generateRefreshToken(String username) {
        long duration = jwtProperties.getRefreshToken().getDuration();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * duration))
                .signWith(refreshKey)
                .compact();
    }

    /**
     * 验证 refreshToken
     */
    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().verifyWith(refreshKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;  // token 不合法或已过期
        }
    }

    /**
     * 生成过期时间
     */
    public Date generateExpiration(@Nonnull TokenType tokenType) {
        long duration;
        if (tokenType == TokenType.AccessToken) {
            duration = jwtProperties.getAccessToken().getDuration();
        } else {
            duration = jwtProperties.getRefreshToken().getDuration();
        }
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60 * duration);
    }

    public String extractUsername(String token) {
        return extractUsername(token, TokenType.AccessToken);
    }

    /**
     * 从 token 中获取用户名
     */
    public String extractUsername(String token, @Nonnull TokenType tokenType) {
        SecretKey secretKey;
        if (tokenType == TokenType.AccessToken) {
            secretKey = accessKey;
        } else {
            secretKey = refreshKey;
        }
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
