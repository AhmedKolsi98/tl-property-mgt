package com.touneslina.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.DialectOverride;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY= "w7rX8Zq+2qf9Jt3U6kV1H8b2l3T0pF4mQyN5vR0s1xE="; // minimum 256 bits for the scret key

    public String extractMail(String token) {
        return extractClaim(token, Claims::getSubject); // Claims::getSubject is a method reference as claims -> claims.getSubject()
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // for Spring the UserName is the identifier, for us, it's the email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact(); // will generate and return the token
                // generated a token out of the UserDetails and the extraClaims
    }

    // a method to validate a token
    public boolean validateToken(String token, UserDetails userDetails) {
        // we want to validate if this token belongs to that userDetails and check the expiration
        final String email = extractMail(token);
        return (email.equals(userDetails.getUsername())) && !expiredToken(token);
    }

    private boolean expiredToken(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder() //Returns a JwtParserBuilder object you can configure.
                .setSigningKey(getSingInKey()) // a secret used to sign a JWT to ensure that the sender of JWT is its owner
                .build() //used the Builder DP
                .parseClaimsJws(token) //to parse our token
                .getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // we will decode our secret key
        return Keys.hmacShaKeyFor(keyBytes); // hmacShaKeyFor is one of the algorithms for generating a secret key
    }
}
