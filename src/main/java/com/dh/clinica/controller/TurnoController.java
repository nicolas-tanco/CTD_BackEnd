package com.dh.clinica.controller;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.dto.TurnoDto;
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
    public ResponseEntity<?> crear(@RequestBody TurnoDto t){
        turnoService.guardar(t);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/semana")
    public ResponseEntity<?> turnosSemanales(){
        return ResponseEntity.ok(turnoService.buscarTurnosProximaSemana());
    }

    @GetMapping("/buscarid/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(turnoService.buscar(id));
    }
}
