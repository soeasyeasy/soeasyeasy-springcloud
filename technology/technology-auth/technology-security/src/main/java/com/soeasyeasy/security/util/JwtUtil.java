package com.soeasyeasy.security.util;

import com.soeasyeasy.common.entity.LoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author hc
 */
@Component
public class JwtUtil {

    public static final String JWT_KEY = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    // 10 天
    private final long EXPIRATION_TIME = 864_000_000;
    //private final long EXPIRATION_TIME = 10;

    /**
     * 生成 JWT Token
     *
     * @param username 用户名（主体）
     * @return JWT Token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(generalKey())
                .compact();
    }

    public String generateToken(LoginDTO loginDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginDTO.getId());
        claims.put("name", loginDTO.getName());
        claims.put("tenantId", loginDTO.getTenantId());
        return Jwts.builder()
                .claims(claims)
                .subject(loginDTO.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(generalKey())
                .compact();
    }

    /**
     * 从 Token 中提取用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getTenantIdFromToken(String token) {
        return (String) getClaims(token).get("tenantId");
    }

    /**
     * 判断 Token 是否过期
     */
    public Boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * 验证 Token 是否有效
     */
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(generalKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }
}