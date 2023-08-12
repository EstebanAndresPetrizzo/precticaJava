package com.practicaEntrevista.precticaJava.services;

import com.practicaEntrevista.precticaJava.DTO.UsuarioDTO;
import com.practicaEntrevista.precticaJava.exception.UserNotFountException;
import com.practicaEntrevista.precticaJava.model.UsuarioModel;
import com.practicaEntrevista.precticaJava.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTO> getUsers(){
        List<UsuarioModel> users = (List<UsuarioModel>) usuarioRepository.findAll();
        return users.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> seveUser(UsuarioDTO usuario){
        UsuarioModel user = usuarioRepository.save(modelMapper.map(usuario, UsuarioModel.class));
        return findById(user.getId());
    }

    public List<UsuarioDTO> findByName(String nombre){
        List<UsuarioModel> users = usuarioRepository.findByNombre(nombre);
        return users.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id){
        Optional<UsuarioModel> user = usuarioRepository.findById(id);
        return user.map(usuarioModel -> modelMapper.map(usuarioModel, UsuarioDTO.class));
    }

    public Boolean deleteUser(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
