package com.ds.dss.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final Logger LOGGER;
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private String generateToken(final Map<String, Object> claims) {
        return Jwts.builder().setClaims((Map) claims).setExpiration(this.generateExpirationDate()).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    private Claims getClaimsFromToken(final String token) {
        Claims claims = null;
        try {
            claims = (Claims) Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            JwtTokenUtil.LOGGER.info("JWT\u683c\u5f0f\u9a8c\u8bc1\u5931\u8d25:{}", (Object) token);
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000L);
    }

    public String getUserNameFromToken(final String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public boolean validateToken(final String token, final UserDetails userDetails) {
        final String username = this.getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        final Date expiredDate = this.getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    private Date getExpiredDateFromToken(final String token) {
        final Claims claims = this.getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public String generateToken(final UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return this.generateToken(claims);
    }

    public boolean canRefresh(final String token) {
        return !this.isTokenExpired(token);
    }

    public String refreshToken(final String token) {
        final Claims claims = this.getClaimsFromToken(token);
        claims.put("created", new Date());
        return this.generateToken((Map<String, Object>) claims);
    }

    static {
        LOGGER = LoggerFactory.getLogger((Class) JwtTokenUtil.class);
    }
}
