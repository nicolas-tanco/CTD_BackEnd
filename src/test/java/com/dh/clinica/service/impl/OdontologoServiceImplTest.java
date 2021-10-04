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
        assertNotNull(service.guardar(od).getId());
        service.eliminar(service.buscarTodos().get(0).getId());
    }

    @Test
    public void eliminarTest() throws InvalidInputException, NotFoundException {
        Integer size1 = service.buscarTodos().size();
        service.eliminar(service.guardar(od).getId());
        Integer size2 = service.buscarTodos().size();
        assertEquals(size1,size2);
    }


    @Test
    void guardar() throws InvalidInputException, NotFoundException {
        od.setId(service.guardar(od).getId());
        assertFalse(service.buscarTodos().isEmpty());
        service.eliminar(od.getId());
    }


    @Test
    void buscarError() throws InvalidInputException, NotFoundException {

        try{
            od.setId(service.guardar(od).getId());
            service.buscar( od.getId()+1);
        }catch (Exception ex){
            assertTrue(ex.getMessage().equals("El id no existe."));
        }
        service.eliminar(od.getId());
    }



    @Test
    void z_actualizar() throws InvalidInputException, NotFoundException {
        od.setId(service.guardar(od).getId());
        od.setApellido("Tanco");
        assertEquals("Tanco",service.actualizar(od).getApellido());
        service.eliminar(od.getId());
    }

}
