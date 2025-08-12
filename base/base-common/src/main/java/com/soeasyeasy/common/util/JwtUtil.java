//package com.soeasyeasy.common.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Encoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
///**
// * jwt util
// *
// * @author hc
// * @date 2025/04/17
// */
//@Component
//public class JwtUtil {
//    @Value("${jwt.secret:gw1zMQeGhOlARrT7CYoRIPZoDHiM6B7qd5CA+C9mkWE=}")
//    private String secret;
//
//    public static void main(String[] args) {
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        System.out.println(Encoders.BASE64.encode(secretKey.getEncoded()));
//    }
//
//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                //7天
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        return claimsResolver.apply(claims);
//    }
//
//    public Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token, String username) {
//        final String extractedUsername = extractUsername(token);
//        return (extractedUsername.equals(username) && !isTokenExpired(token));
//    }
//}
