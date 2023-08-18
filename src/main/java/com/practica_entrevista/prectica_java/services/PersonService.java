package com.practica_entrevista.prectica_java.services;

import com.practica_entrevista.prectica_java.dto.PersonDTO;
import com.practica_entrevista.prectica_java.exceptions.ResourceNotFountException;
import com.practica_entrevista.prectica_java.model.PersonModel;
import com.practica_entrevista.prectica_java.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<PersonDTO> getUsers(){
        List<PersonModel> users = (List<PersonModel>) personRepository.findAll();
        return users.stream()
                .map(usuario -> modelMapper.map(usuario, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<PersonDTO> seveUser(PersonDTO usuario){
        PersonModel user = personRepository.save(modelMapper.map(usuario, PersonModel.class));
        return findById(user.getId());
    }

    public List<PersonDTO> findByName(String nombre){
        List<PersonModel> users = personRepository.findByNombre(nombre);
        if (users.isEmpty()){
            throw new ResourceNotFountException("No se encontro una persona con el nombre indicado: " + nombre);
        }
        return users.stream()
                .map(usuario -> modelMapper.map(usuario, PersonDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<PersonDTO> findById(Long id){
        Optional<PersonModel> user = personRepository.findById(id);
        if (user.isEmpty()){
            throw new ResourceNotFountException("No se encontro el recurso con id: " + id);
        }
        return user.map(personModel -> modelMapper.map(personModel, PersonDTO.class));
    }

    public ResponseEntity<String> deletePerson(Long id) throws ResourceNotFountException {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>("El usuario se ha eliminado correctamente", HttpStatus.OK);
        }catch (Exception err){
            throw new ResourceNotFountException("No se encontro el usuario indicado");
        }
    }
}
