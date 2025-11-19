package com.kujojoshua.gestor_tareas_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.service.TareaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/tareas")
@RequiredArgsConstructor
public class TareasController {

    private final TareaService tareaService;

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> updateTarea(@Valid @PathVariable Long id, @RequestBody TareaDTO tareaDTO){
        
        TareaDTO tareaActualizada=tareaService.actualizarTarea(id, tareaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(tareaActualizada);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id){
        tareaService.eliminarTareaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    
    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody TareaDTO tareaDTO ) {
        tareaDTO=tareaService.crearTarea(tareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaDTO);
    }

    @GetMapping
    public ResponseEntity<List<TareaDTO>> listaTodas(){
        List<TareaDTO> tareaDTOs= tareaService.mostrarTodas();

        return ResponseEntity.status(HttpStatus.OK).body(tareaDTOs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> getTareaPorId(@PathVariable("id") Long idTarea) {
        TareaDTO tareaEncontrada=tareaService.buscarTareaPorId(idTarea);
        
        return ResponseEntity.status(HttpStatus.OK).body(tareaEncontrada);
    }
    
    
}
