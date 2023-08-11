package com.practicaEntrevista.precticaJava.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String email;

}
