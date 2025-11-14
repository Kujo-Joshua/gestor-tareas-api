package com.kujojoshua.gestor_tareas_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.service.TareaService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareasController {

    private final TareaService tareaService;


    @PostMapping("/crear")
    public Tarea crearTarea(@RequestBody TareaDTO tareaDTO ) {
        Tarea tareaGuardada=tareaService.crearTarea(tareaDTO);
        return tareaGuardada;
    }
    
    
}
