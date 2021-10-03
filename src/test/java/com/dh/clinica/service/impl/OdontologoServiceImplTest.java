package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
class OdontologoServiceImplTest {

    @Autowired
    OdontologoServiceImpl service;
    private OdontologoDto od = new OdontologoDto();


    @Test
    void buscar() throws InvalidInputException, NotFoundException {
        service.guardar(od);
        assertNotNull(service.buscar(1));
        service.eliminar(service.buscarTodos().get(0).getId());
    }

    @Test
    public void eliminarTest() throws InvalidInputException, NotFoundException {
        service.guardar(od);
        service.eliminar(service.buscarTodos().get(0).getId());
        assertTrue(service.buscarTodos().isEmpty());
    }

    @Test
    void guardar() throws InvalidInputException, NotFoundException {
        service.guardar(od);
        assertFalse(service.buscarTodos().isEmpty());
        service.eliminar(service.buscarTodos().get(0).getId());
    }


    @Test
    void buscarError() {

        try{
            service.guardar(od);
            service.buscar(1);
            service.eliminar(service.buscarTodos().get(0).getId());
        }catch (Exception ex){
            assertTrue(ex.getMessage().equals("El id no existe."));
        }
    }



    @Test
    void z_actualizar() throws InvalidInputException, NotFoundException {
        service.guardar(od);
        assertNotNull(service.actualizar(service.buscarTodos().get(0)));
        service.eliminar(service.buscarTodos().get(0).getId());
    }

}
