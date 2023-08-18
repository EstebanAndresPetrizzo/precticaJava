package com.practica_entrevista.prectica_java.repositories;

import com.practica_entrevista.prectica_java.model.PersonModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PersonRepository extends CrudRepository<PersonModel, Long> {
    ArrayList<PersonModel> findByNombre(String name);
}
