package com.kujojoshua.gestor_tareas_api.security;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kujojoshua.gestor_tareas_api.model.Usuario;
import com.kujojoshua.gestor_tareas_api.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(usernameOrEmail)
                .or(()-> usuarioRepository.findByEmail(usernameOrEmail))
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con ese email: " + usernameOrEmail));

        //Convertimos nuestro "Usuario" (Dominio) al "User" de spring Security
        //Por ahora hardcodeamos el rol "ROLE_USER" hasta que tengamos tabla de roles
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(usuario.getEmail(), usuario.getPassword(), authorities);
    }
    
    
}
