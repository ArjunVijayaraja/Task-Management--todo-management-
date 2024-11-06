package com.example.todo_management.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;

@Service
public class JwtUtil {
    //expire in milli Seconds
    private static final int expireInMs = 300*1000;
    //THe below method .secretKeyFor() is a depricated method
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generate(String username){
        //****************THIS is the key ---just printing for reference only
        System.out.println("THis is the KEY : " +key);
       return Jwts.builder()
                .setSubject(username)
                .setIssuer("test.com") //our company name or any specific ame rrelated to our organization
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireInMs))
                .signWith(key)
                .compact();

    }

    public boolean validate(String token){
        if(getUsername(token) != null && isExpired(token)){
            return true;
        }
        return false;
    }

    private boolean isExpired(String token) {
       // Claims claims = Jwts.parser().setSigningKey(key).build().parseSignedClaims(token).getPayload();
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));

    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        System.out.println(claims.getSubject());
       return claims.getSubject();

    }

    private static Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims;
    }


}
