package com.kujojoshua.gestor_tareas_api.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

import org.springframework.security.core.Authentication;

@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-milliseconds}")
    private long jwtExpirationInMs;

    public String generarToken(Authentication authentication){
        String username= authentication.getName();
        Date fechaActual =new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime()+jwtExpirationInMs);

        Key clave = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(fechaActual)
                .setExpiration(fechaExpiracion)
                .signWith(clave, SignatureAlgorithm.HS512)
                .compact();
    }


    public String obtenerUsernameDelJwt(String token){
        Key clave = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        Claims claims = Jwts.parserBuilder()
                        .setSigningKey(clave)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String token){
        try {
            Key clave = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parserBuilder().setSigningKey(clave).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            System.out.println("Firma no valida");
        } catch(MalformedJwtException e){
            System.out.println("Token Jwt no valido");
        } catch(ExpiredJwtException e){
            System.out.println("Token JWT caducado");
        } catch(UnsupportedJwtException e){
            System.out.println("Token JWT no compatible");
        } catch (IllegalArgumentException e){
            System.out.println("la cadena claims JWT esta vacia");
        }
        return false;
    }
}
