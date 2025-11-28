package com.kujojoshua.gestor_tareas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    @NotBlank @Size(min = 5, max = 15)
    private String username;
    @NotBlank
    private String password;
}
