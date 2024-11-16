package com.tpc.groot.jwt;

import com.tpc.groot.user.entity.CustomUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey key;
    private final JwtParser parser;

    public TokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperties.getSecret()));
        parser = Jwts.parser().verifyWith(key).build();
    }

    public String generateToken(CustomUser customUser, Duration expiredAt) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expiredAt.toMillis());

        return Jwts.builder()
                .header().add("type", "JWT").add("alg", "HS256").and()
                .claims()
                    .issuer(jwtProperties.getIssuer())
                    .issuedAt(now)
                    .expiration(expiry)
                    .subject(customUser.getUsername())
                    .add("id", customUser.getId())
                    .and()
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(claims.getSubject())
                .password("")
                .authorities(authorities)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }

    public Claims getClaims(String token){
        Jws<Claims> jws = parser.parseSignedClaims(token);
        return jws.getPayload();
    }
}
