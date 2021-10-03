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
        odontologo.setId(1);
        paciente.setId(1);
        paciente.setDomicilio(domicilio);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDateTime.now());
        servicePaciente.guardar(paciente);
        serviceOdontologo.guardar(odontologo);

    }

    @Test
    void test1_guardar() throws Exception{

        serviceTurno.guardar(turno);
        assertTrue(!serviceTurno.buscarTodos().isEmpty());
        serviceTurno.eliminar(serviceTurno.buscarTodos().get(0).getId());

    }

    @Test
    void test2_eliminar()throws NotFoundException, InvalidInputException {


        serviceTurno.guardar(turno);
        serviceTurno.eliminar(serviceTurno.buscarTodos().get(0).getId());

        assertTrue(serviceTurno.buscarTodos().isEmpty());
    }

    @Test
    void test3_actualizar_error() throws NotFoundException {



       try {
           serviceTurno.guardar(turno);
           turno.setId(serviceTurno.buscarTodos().get(0).getId());
           serviceTurno.actualizar(turno);
       }catch (Exception ex){
           assertTrue(ex.getMessage().equals("Horario ocupado"));
       }
        serviceTurno.eliminar(serviceTurno.buscarTodos().get(0).getId());

    }



    @Test
    void semanales() throws InvalidInputException, NotFoundException {
        turno.setFecha(LocalDateTime.now().plusDays(4));
        serviceTurno.guardar(turno);
        FechaDto fechaDto = new FechaDto(LocalDateTime.now().getDayOfMonth(),LocalDateTime.now().getMonthValue(),LocalDateTime.now().getYear());
        assertTrue(!serviceTurno.semanales(fechaDto).isEmpty());
        serviceTurno.eliminar(serviceTurno.buscarTodos().get(0).getId());
    }
}