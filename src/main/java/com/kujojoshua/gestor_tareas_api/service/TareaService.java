package com.kujojoshua.gestor_tareas_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kujojoshua.gestor_tareas_api.Exception.ResourceNotFoundException;
import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.model.Usuario;
import com.kujojoshua.gestor_tareas_api.repository.TareaRepository;
import com.kujojoshua.gestor_tareas_api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


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
    public void eliminarTareaPorId(Long id){
        Tarea tareaAEliminar=tareaRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("La tarea con el id: "+id+ " No fue encontrada"));
        tareaRepository.delete(tareaAEliminar);
    }

    @Transactional
    public TareaDTO crearTarea(TareaDTO tareaDTO, String username){
        Usuario usuario= usuarioRepository.findByUsername(username)
        .or(()-> usuarioRepository.findByEmail(username))
        .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado: " + username));
    

        Tarea tarea=new Tarea();
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setUsuario(usuario);
        Tarea tareaGuardada=tareaRepository.save(tarea);
        
        return convertirADTO(tareaGuardada);
    }

    @Transactional
    public TareaDTO actualizarTarea(Long id, TareaDTO tareaDTO){
        Tarea tarea= tareaRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("La tarea con el id: "+id+ " No fue encontrada"));
        tarea.setTitulo(tareaDTO.getTitulo());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setCompletada(tareaDTO.isCompletada());
        return convertirADTO(tarea);
    }

    @Transactional
    public List<TareaDTO> mostrarTodas(){
        List<Tarea> tareas= tareaRepository.findAll();
        
        return tareasADTO(tareas);
    } 

    @Transactional
    public List<TareaDTO> obtenerTareasPorUsuario(String username){
        Usuario usuario= usuarioRepository.findByUsername(username)
        .or(()->usuarioRepository.findByEmail(username))
        .orElseThrow(()-> new UsernameNotFoundException("usuario no encontrado"));

        List<Tarea> tareas =tareaRepository.findByUsuarioId(usuario.getId());

        return tareas.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Transactional
    public TareaDTO buscarTareaPorId(Long id){
        Tarea tareaEncontrada= tareaRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("La tarea con el id: "+id+ " No fue encontrada"));
        return convertirADTO(tareaEncontrada);
    }
}
