package com.pieropan.getstore.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken(String email) {
        return Jwts.builder().claim("email", email).signWith(key).setExpiration(getData()).compact();
    }

    private Date getData() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);

        return calendar.getTime();
    }

    public boolean tokenValidado(String token) {
        return getClaims(token).getExpiration().after(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}