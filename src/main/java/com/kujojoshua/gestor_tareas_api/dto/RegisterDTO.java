package com.kujojoshua.gestor_tareas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank @Size(max = 35)
    private String nombre;
    @NotBlank @Size(min = 7, max = 20)
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
