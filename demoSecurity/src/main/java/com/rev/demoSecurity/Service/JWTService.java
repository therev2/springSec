package com.rev.demoSecurity.Service;

import com.rev.demoSecurity.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    String secret="";

    public JWTService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
        SecretKey sec=keyGen.generateKey();
        secret=Base64.getEncoder().encodeToString(sec.getEncoded());
    }

    public String generate(Users user) {
        Map<String,Object> claims=new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*24*30)) //30 days expiration
                .and()
                .signWith(getKey())
                .compact();
//        return "ok";
    }
    public Key getKey() {
//        String sec="cF7.9!R";
        byte[] encoded= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(encoded);

    }
}
