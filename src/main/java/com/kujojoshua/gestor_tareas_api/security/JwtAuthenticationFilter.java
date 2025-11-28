package com.kujojoshua.gestor_tareas_api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //Extiende esa clase para que se ejecute una vez por peticion
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService; //Esto se implementa despues

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //1. Obtenemos el token de la solicitud
        String token= obtenerTokenDeSolicitud(request);

        //2.Validamos el token
        if(StringUtils.hasText(token) && tokenProvider.validarToken(token)){
            //Obtenemos el username del token
            String username = tokenProvider.obtenerUsernameDelJwt(token);

            //Cargamos el usuario asociado al token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Creamos la autenticacion
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //Establecemos la seguridad en el contexto
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        //continuamos con la cadena de filtros (pase lo que pase)
        filterChain.doFilter(request, response);

    }

    private String obtenerTokenDeSolicitud(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); //Quitamos "Bearer " y devolvemos solo el token 
        }
        return null;
    }

    
    
}
