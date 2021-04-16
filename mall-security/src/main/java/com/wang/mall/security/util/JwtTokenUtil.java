package com.wang.mall.security.util;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

/**
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String generateToken(Map<String,Object> claim) {
        return Jwts.builder()
                .setClaims(claim)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> map = new HashMap<>();
        map.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        map.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(map);
    }
}
