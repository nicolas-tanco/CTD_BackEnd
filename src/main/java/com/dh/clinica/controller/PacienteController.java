package com.dh.clinica.controller;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired

    PacienteService pacienteService;


    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> getAll() {
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @PostMapping("/nuevo")
    public ResponseEntity<PacienteDto> crearNuevoPaciente(@RequestBody PacienteDto paciente) throws InvalidInputException {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@RequestBody PacienteDto paciente) throws InvalidInputException, NotFoundException {
        if(pacienteService.buscar( paciente.getId() )!= null) {
            return ResponseEntity.ok(pacienteService.actualizar(paciente));
        }else{
            return ResponseEntity.badRequest().body(paciente);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws InvalidInputException, NotFoundException {
        PacienteDto o = pacienteService.buscar(id);
        if(o!=null){
            return ResponseEntity.ok(o);
        }else{
            return ResponseEntity.badRequest().body("El id no existe");
        }
    }

}
