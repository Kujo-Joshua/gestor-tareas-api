package com.kujojoshua.gestor_tareas_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TareaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private boolean completada;
}
