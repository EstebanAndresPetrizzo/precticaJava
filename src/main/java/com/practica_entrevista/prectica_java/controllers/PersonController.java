package com.practica_entrevista.prectica_java.controllers;

import com.practica_entrevista.prectica_java.dto.PersonDTO;
import com.practica_entrevista.prectica_java.exceptions.ResourceNotFountException;
import com.practica_entrevista.prectica_java.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public List<PersonDTO> getUsers(){
        return personService.getUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<PersonDTO> saveUser(@RequestBody PersonDTO usuario){
        return this.personService.seveUser(usuario);
    }

    @GetMapping (path = "/search")
    public List<PersonDTO> findByName(@RequestParam("nombre") String name) throws ResourceNotFountException {
        return this.personService.findByName(name);
    }

    @GetMapping (path = "/search/{id}")
    public Optional<PersonDTO> findById(@PathVariable("id") Long id)throws ResourceNotFountException {
        return this.personService.findById(id);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Long id) throws ResourceNotFountException {
        return this.personService.deletePerson(id);
    }
}
