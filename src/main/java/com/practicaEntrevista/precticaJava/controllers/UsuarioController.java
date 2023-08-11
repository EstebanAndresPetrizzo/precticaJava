package com.practicaEntrevista.precticaJava.controllers;

import com.practicaEntrevista.precticaJava.model.UsuarioModel;
import com.practicaEntrevista.precticaJava.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping (path = "/query")
    public ArrayList<UsuarioModel> findByNombre(@RequestParam("nombre") String nombre){
        return this.usuarioService.findByNombre(nombre);
    }

    @GetMapping (path = "/{id}")
    public Optional<UsuarioModel> findById(@PathVariable("id") Long id){
        return this.usuarioService.findById(id);
    }



    @DeleteMapping (path = "/{id}")
    public Boolean eliminarUsuario(@PathVariable("id") Long id){
        return this.usuarioService.eliminarUsuario(id);
    }
}
