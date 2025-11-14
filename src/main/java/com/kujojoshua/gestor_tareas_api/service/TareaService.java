package com.kujojoshua.gestor_tareas_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.repository.TareaRepository;

import jakarta.transaction.Transactional;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository;

    @Transactional
    public Tarea crearTarea(TareaDTO tareaDTO){
        Tarea tarea=new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        Tarea tareaGuardada=tareaRepository.save(tarea);
        return tareaGuardada;
    }

    @Transactional
    public List<Tarea> mostrarTodas(){
        List<Tarea> tareas= tareaRepository.findAll();
        return tareas;
    } 
}
