package com.kujojoshua.gestor_tareas_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kujojoshua.gestor_tareas_api.Exception.ResourceNotFoundException;
import com.kujojoshua.gestor_tareas_api.dto.TareaDTO;
import com.kujojoshua.gestor_tareas_api.model.Tarea;
import com.kujojoshua.gestor_tareas_api.repository.TareaRepository;

@ExtendWith(MockitoExtension.class) //le decimos a junit que use mockito
public class TareaServiceTest {
    @Mock //Crea un "doble" del repo, no es real. nosotros le decimos que hacer
    private TareaRepository tareaRepository;
    @InjectMocks// Inyecta el repositorio falso dentro del servicio real
    private TareaService tareaService;

    //tests
    @Test
    void deberiaCrearTareaExitosamente(){
        TareaDTO tareaDTO= new TareaDTO();
        tareaDTO.setTitulo("Estudiar Mockito");
        Tarea tareaGuardada=new Tarea();
        tareaGuardada.setTitulo("Estudiar Mockito");

        when(tareaRepository.save(any(Tarea.class))).thenReturn(tareaGuardada);

        TareaDTO resultado=tareaService.crearTarea(tareaDTO);
        assertNotNull(resultado);
        assertEquals(tareaGuardada.getTitulo(), resultado.getTitulo());
    }

    @Test
    void deberiaLanzarExcepcionCuandoTareaNoExiste(){
        when(tareaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->{
            tareaService.buscarTareaPorId(99L);
        });
    }
}
