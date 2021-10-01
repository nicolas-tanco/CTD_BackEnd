package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    @Autowired
    OdontologoService service;

    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarPorId(@PathVariable Integer id) throws InvalidInputException, NotFoundException {
        OdontologoDto o = service.buscar(id);
       return ResponseEntity.ok(o);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<OdontologoDto> crear(@RequestBody OdontologoDto o) throws InvalidInputException, NotFoundException {
        return ResponseEntity.ok(service.guardar(o));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizar(@RequestBody OdontologoDto o) throws InvalidInputException, NotFoundException {
        if(service.buscar(o.getId()) != null)
            return ResponseEntity.ok(service.guardar(o));
        else
            return ResponseEntity.badRequest().body(o);
    }
}
