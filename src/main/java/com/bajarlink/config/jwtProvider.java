package com.bajarlink.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class jwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(jwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAutjorities(authorities);

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 24*60*60*1000))
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        String jwt = token.substring(7);
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
    }

    private String populateAutjorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<String>();

        for(GrantedAuthority ga : authorities) {
            auths.add(ga.getAuthority());
        }

        return String.join(",", auths);
    }
}
