package com.kujojoshua.gestor_tareas_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.service.TareaService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareasController {

    private final TareaService tareaService;


    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody TareaDTO tareaDTO ) {
        Tarea tareaGuardada=tareaService.crearTarea(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaGuardada);
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listaTodas(){
        List<Tarea> tareas=tareaService.mostrarTodas();
        return ResponseEntity.status(HttpStatus.OK).body(tareas);
    }
    
    
}
