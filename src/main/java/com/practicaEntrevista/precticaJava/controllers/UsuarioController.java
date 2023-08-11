package com.practicaEntrevista.precticaJava.controllers;

import com.practicaEntrevista.precticaJava.services.UsuarioDTO;
import com.practicaEntrevista.precticaJava.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getUsers(){
        return usuarioService.getUsers();
    }

    @PostMapping
    public UsuarioDTO saveUser(@RequestBody UsuarioDTO usuario){
        return this.usuarioService.seveUser(usuario);
    }

    @GetMapping (path = "/query")
    public List<UsuarioDTO> findByName(@RequestParam("nombre") String name){
        return this.usuarioService.findByName(name);
    }

    @GetMapping (path = "/{id}")
    public Optional<UsuarioDTO> findById(@PathVariable("id") Long id){
        return this.usuarioService.findById(id);
    }

    @DeleteMapping (path = "/{id}")
    public Boolean deleteUser(@PathVariable("id") Long id){
        return this.usuarioService.deleteUser(id);
    }
}
