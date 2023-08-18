package com.practica_entrevista.prectica_java.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica_entrevista.prectica_java.dto.PersonDTO;
import com.practica_entrevista.prectica_java.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matcher.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;

    ObjectMapper objectsMapper;

    @BeforeEach
    void setup() {
        objectsMapper = new ObjectMapper();
    }

    @Test
    void getUsers() {
        when(personService.getUsers());
        try {
            mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void saveUser() throws Exception {
        PersonDTO carlos = new PersonDTO(null, "Carlos", "carlos@gmail.com");
        when(personService.seveUser(any())).then(invocation ->{
            PersonDTO p =invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(objectsMapper.writeValueAsString(carlos)))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("email", is("carlos@gmail.com")));

        verify(personService).seveUser(any());
    }

    @Test
    void findByName() {
    }

    @Test
    void findById() {
    }

    @Test
    void deletePerson() {
    }
}