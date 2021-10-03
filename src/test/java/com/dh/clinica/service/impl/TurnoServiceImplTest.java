package com.dh.clinica.service.impl;

import com.dh.clinica.dto.*;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TurnoServiceImplTest {

    @Autowired
    TurnoServiceImpl serviceTurno;
    @Autowired
    OdontologoServiceImpl serviceOdontologo;
    @Autowired
    PacienteServiceImpl servicePaciente;

    private  OdontologoDto odontologo = new OdontologoDto();
    private  PacienteDto paciente = new PacienteDto();
    private  DomicilioDto domicilio = new DomicilioDto();
    private  TurnoDto turno = new TurnoDto();

    @BeforeEach
    void setup() throws InvalidInputException {
        paciente.setDomicilio(domicilio);
        turno.setFecha(LocalDateTime.now());
        paciente = servicePaciente.guardar(paciente);
        odontologo = serviceOdontologo.guardar(odontologo);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

    }

    @Test
    void test1_guardar() throws Exception{

        turno = serviceTurno.guardar(turno);
        assertTrue(!serviceTurno.buscarTodos().isEmpty());
        serviceTurno.eliminar(turno.getId());

    }

    @Test
    void test2_eliminar()throws NotFoundException, InvalidInputException {

        turno = serviceTurno.guardar(turno);
        assertNotNull(serviceTurno.eliminar(turno.getId()));
    }

    @Test
    void test3_actualizar_error() throws NotFoundException {

       try {
           turno = serviceTurno.guardar(turno);
           serviceTurno.actualizar(turno);
       }catch (Exception ex){
           assertTrue(ex.getMessage().equals("Horario ocupado"));
       }
        serviceTurno.eliminar(turno.getId());

    }



    @Test
    void semanales() throws InvalidInputException, NotFoundException {

        turno.setFecha(LocalDateTime.now().plusDays(4));
        turno = serviceTurno.guardar(turno);
        FechaDto fechaDto = new FechaDto(LocalDateTime.now().getDayOfMonth(),LocalDateTime.now().getMonthValue(),LocalDateTime.now().getYear());
        assertFalse(serviceTurno.semanales(fechaDto).isEmpty());
        serviceTurno.eliminar(turno.getId());
    }
}