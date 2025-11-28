package com.kujojoshua.gestor_tareas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kujojoshua.gestor_tareas_api.dto.AuthResponseDTO;
import com.kujojoshua.gestor_tareas_api.dto.LoginDTO;
import com.kujojoshua.gestor_tareas_api.dto.RegisterDTO;
import com.kujojoshua.gestor_tareas_api.model.Usuario;
import com.kujojoshua.gestor_tareas_api.repository.UsuarioRepository;
import com.kujojoshua.gestor_tareas_api.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    //Endpoint del registro
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterDTO registerDTO) {
        if (usuarioRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Ese username ya esta tomado", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Ese email ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(registerDTO.getNombre());
        usuario.setEmail(registerDTO.getEmail());
        usuario.setUsername(registerDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
    }
    

    
    //Endpoint del login:
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication =authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        //Establecer el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generamos el token
        String token=jwtTokenProvider.generarToken(authentication);

        //Devolvemos el token
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
    
}
