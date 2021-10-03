package com.dh.clinica.controller;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.dto.FechaDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repositories.OdontologoRepository;
import com.dh.clinica.persistence.repositories.TurnoRepository;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import com.dh.clinica.service.impl.OdontologoServiceImpl;
import com.dh.clinica.service.impl.PacienteServiceImpl;
import com.dh.clinica.service.impl.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoServiceImpl turnoService;

    @Autowired
    SpringConfig springConfig;

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody TurnoDto t) throws InvalidInputException, NotFoundException {

        return ResponseEntity.ok( turnoService.guardar(t));
    }

    @GetMapping("/semana")
    public ResponseEntity<?> turnosSemanales1(@RequestBody FechaDto f){
        return ResponseEntity.ok(turnoService.semanales(f));
    }

    @GetMapping("/buscarid/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(turnoService.buscar(id));
    }
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto t) throws InvalidInputException, NotFoundException {
        return ResponseEntity.ok(turnoService.guardar(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(turnoService.eliminar(id));
    }

}
