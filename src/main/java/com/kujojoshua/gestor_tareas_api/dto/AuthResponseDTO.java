package com.kujojoshua.gestor_tareas_api.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer";


    public AuthResponseDTO(String accesToken){
        this.accessToken=accesToken;
    }
}
