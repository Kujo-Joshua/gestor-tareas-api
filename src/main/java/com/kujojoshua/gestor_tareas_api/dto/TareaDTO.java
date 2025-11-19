package com.kujojoshua.gestor_tareas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TareaDTO {
    private Long id;
    @NotBlank @Size(min = 3, max = 100)
    private String titulo;
    @Size(max = 255)
    private String descripcion;
    private boolean completada;
}
