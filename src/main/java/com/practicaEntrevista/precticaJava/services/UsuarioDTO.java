package com.practicaEntrevista.precticaJava.services;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }
}

