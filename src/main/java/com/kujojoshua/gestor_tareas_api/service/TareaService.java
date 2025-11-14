package com.kujojoshua.gestor_tareas_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kujojoshua.gestor_tareas_api.Exception.ResourceNotFoundException;
import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.repository.TareaRepository;

import jakarta.transaction.Transactional;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository;

    private TareaDTO convertirADTO(Tarea tarea){
        return new TareaDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion(), tarea.isCompletada());
    }

    private List<TareaDTO> tareasADTO(List<Tarea> tareas){
        List<TareaDTO> tareasDTO = new ArrayList<>();
        for(int i=0; i<tareas.size(); i++){
            tareasDTO.add(convertirADTO(tareas.get(i)));
        }
        return tareasDTO;
    }

    @Transactional
    public TareaDTO crearTarea(TareaDTO tareaDTO){
        Tarea tarea=new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        Tarea tareaGuardada=tareaRepository.save(tarea);
        return convertirADTO(tareaGuardada);
    }

    @Transactional
    public List<TareaDTO> mostrarTodas(){
        List<Tarea> tareas= tareaRepository.findAll();
        
        return tareasADTO(tareas);
    } 

    @Transactional
    public TareaDTO buscarTareaPorId(Long id){
        Tarea tareaEncontrada= tareaRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Tarea no encontrado con el id: "+id));
        return convertirADTO(tareaEncontrada);
    }
}
