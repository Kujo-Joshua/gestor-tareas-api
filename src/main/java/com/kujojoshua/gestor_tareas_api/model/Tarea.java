package com.kujojoshua.gestor_tareas_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
public class Tarea {
    
    @Getter @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//se genera autoincremental
    private Long id;

    @Getter @Setter
    private String titulo;

    @Getter @Setter
    private String descripcion;

    @Getter @Setter
    private boolean completada=false;


}
