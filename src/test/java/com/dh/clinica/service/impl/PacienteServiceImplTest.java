package com.dh.clinica.service.impl;

import com.dh.clinica.dto.DomicilioDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Access;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceImplTest {

    @Autowired
    PacienteServiceImpl service;
    private PacienteDto paciente = new PacienteDto();
    private DomicilioDto domicilio = new DomicilioDto();

    @BeforeEach
    void setup(){
    paciente.setDomicilio(domicilio);
    }

    @Test
    void guardar() throws Exception{
        service.guardar(paciente);
        assertFalse(service.buscarTodos().isEmpty());
        service.eliminar(service.buscarTodos().get(0).getId());
    }

    @Test
    void buscar() throws InvalidInputException, NotFoundException {
        service.guardar(paciente);
        assertNotNull(service.buscar(1));
        service.eliminar(service.buscarTodos().get(0).getId());
    }


    @Test
    void z_actualizar() throws Exception{
        service.guardar(paciente);
        paciente.setDni("333");
        paciente.setId(service.buscarTodos().get(0).getId());
        service.actualizar(paciente);
        assertTrue(service.buscarTodos().get(0).getDni().equals("333"));
        service.eliminar(service.buscarTodos().get(0).getId());
        paciente.setId(null);
    }

    @Test
    void eliminar() throws Exception{
        service.guardar(paciente);
        service.eliminar(service.buscarTodos().get(0).getId());
        assertTrue(service.buscarTodos().isEmpty());
    }
}