package com.kujojoshua.gestor_tareas_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kujojoshua.gestor_tareas_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
