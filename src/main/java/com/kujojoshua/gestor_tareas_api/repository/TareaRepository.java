package com.kujojoshua.gestor_tareas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kujojoshua.gestor_tareas_api.model.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{
    
}
