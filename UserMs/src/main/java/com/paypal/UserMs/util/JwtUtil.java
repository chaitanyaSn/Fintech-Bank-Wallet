package com.paypal.UserMs.util;


import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final Long JWT_TOKEN_VALIDITY = 5 * 60 * 60L;
    private static final String SECRET = "ubLgVHPbtgzD8qKXqBWRjgrGxJp27VXNxvuKR3dvNPvMyUxDXo0LC5cYFGQBpyQw56y6Xe8xHkxU2lD4N1TM7w==";


    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        CustomsUserDetail user = (CustomsUserDetail) userDetails;

        claims.put("id",user.getId());
        claims.put("email",user.getEmail());
        claims.put("name",user.getName());
        claims.put("walletId",user.getWalletId());
        return doGenerateToken(claims, userDetails.getUsername());

    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();

    }
}
