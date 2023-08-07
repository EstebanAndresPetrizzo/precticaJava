package com.practicaEntrevista.precticaJava.repositories;

import com.practicaEntrevista.precticaJava.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
}
