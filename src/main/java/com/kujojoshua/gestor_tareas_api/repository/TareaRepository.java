package com.kujojoshua.gestor_tareas_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kujojoshua.gestor_tareas_api.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
    
    //Spring Data Jpa traduce esto a SELECT * FROM tarea WHERE usuario_id= ?
    List<Tarea> findByUsuarioId(Long usuarioId);
}
