package com.practica_entrevista.prectica_java.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDTO {
    private Long id;
    private String nombre;
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}

