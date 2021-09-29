package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.persistence.entities.Domicilio;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.persistence.repositories.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository repository;



    public PacienteDto guardar(PacienteDto o) throws InvalidInputException {

        if(o.getId()!=null && repository.findById(o.getId()).isPresent())
            throw new InvalidInputException("El paciente ya existe");
        if(o.getDomicilio()==null)
            throw new InvalidInputException("El paciente tiene que tener domicilio");
        o.setId(repository.save(o.toEntity()).getId());
        return o;
    }

    public PacienteDto buscar(Integer id) throws InvalidInputException, NotFoundException {
        if(id==null)
            throw new InvalidInputException("El id no puede ser null");
        Optional<Paciente> o = repository.findById(id);
        if(o.isEmpty())
            throw new NotFoundException("El id "+id+" no existe.");
        return new PacienteDto(o.get());
    }

    public List<PacienteDto> buscarTodos() {

        List<PacienteDto> pacientes = new ArrayList<>();

        for(Paciente p : repository.findAll()){
            pacientes.add(new PacienteDto(p));
        }

        return pacientes;
    }


    public PacienteDto actualizar(PacienteDto p) throws InvalidInputException {

        Optional <Paciente> o1 = repository.findById(p.getId());
        if(o1.isEmpty())
            throw new InvalidInputException("El paciente no existe");
        if(p.getApellido()!=null)
            o1.get().setApellido(p.getApellido());
        if(p.getNombre()!=null)
            o1.get().setNombre(p.getNombre());
        if(p.getDomicilio()!=null)
            o1.get().setDomicilio(p.getDomicilio().toEntity());
        if(p.getDni()!=null)
            o1.get().setDni(p.getDni());
        repository.save(o1.get());
        return new PacienteDto(repository.findById(p.getId()).get());
    }

    public PacienteDto eliminar(Integer id) throws NotFoundException {
        Optional <Paciente> o = repository.findById(id);
        if(o.isEmpty())
            throw new NotFoundException("No existe el paciente con el id "+id);
        repository.deleteById(id);
        return new PacienteDto(o.get());
    }
}
