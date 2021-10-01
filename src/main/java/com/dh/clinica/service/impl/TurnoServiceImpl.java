package com.dh.clinica.service.impl;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.TurnoDtoRespuestaSemanal;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repositories.TurnoRepository;
import com.dh.clinica.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    TurnoRepository repository;
    @Autowired
    OdontologoServiceImpl odontologoService;
    @Autowired
    PacienteServiceImpl pacienteService;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    SpringConfig springConfig;


    @Override
    public TurnoDto guardar(TurnoDto t) throws InvalidInputException, NotFoundException {
        if(t.getId()!=null){
            if(repository.findById(t.getId()).isPresent())
                throw new InvalidInputException("Turno ya existe");
        }
        if(t.getFecha()==null || t.getPaciente().getId()==null || t.getOdontologo().getId()==null)
            throw new InvalidInputException("Faltan datos");
        if(validacionFechaPaciente(t.getPaciente().getId(), t.getFecha()) || validacionFechaOdontologo(t.getOdontologo().getId(),t.getFecha()))
            throw new InvalidInputException("Horario ocupado");
        if(pacienteService.buscar(t.getPaciente().getId())==null || odontologoService.buscar(t.getOdontologo().getId())==null)
            throw new InvalidInputException("Id inexistente");
        t.setPaciente(pacienteService.buscar(t.getPaciente().getId()));
        t.setOdontologo(odontologoService.buscar(t.getOdontologo().getId()));
        return mapper.convertValue(repository.save(t.toEntity()),TurnoDto.class);
    }

    @Override
    public TurnoDto buscar(Integer id) throws NotFoundException {
        Optional<Turno> opt = repository.findById(id);
        if(opt.isEmpty())
            throw new NotFoundException("Id inexistente");
        return mapper.convertValue(opt.get(), TurnoDto.class);
    }

    @Override
    public List<TurnoDto> buscarTodos() {
        return repository.findAll().stream().map(element->springConfig.getModelMapper().map(element,TurnoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TurnoDto actualizar(TurnoDto t) throws InvalidInputException, NotFoundException {
        Optional<Turno> opt = repository.findById(t.getId());
        if(opt.isEmpty())
            throw new InvalidInputException("Turno inexistente");
        if(t.getFecha()==null || t.getPaciente().getId()==null || t.getOdontologo().getId()==null)
            throw new InvalidInputException("Faltan datos");
        if(validacionFechaPaciente(t.getPaciente().getId(), t.getFecha()) || validacionFechaOdontologo(t.getOdontologo().getId(),t.getFecha()))
            throw new InvalidInputException("Horario ocupado");
        if(pacienteService.buscar(t.getPaciente().getId())==null || odontologoService.buscar(t.getOdontologo().getId())==null)
            throw new InvalidInputException("Id inexistente");
        t.setPaciente(pacienteService.buscar(t.getPaciente().getId()));
        t.setOdontologo(odontologoService.buscar(t.getOdontologo().getId()));

        return mapper.convertValue(repository.save(t.toEntity()),TurnoDto.class);
    }

    public TurnoDto eliminar (Integer id) throws NotFoundException {
        Optional<Turno> opt = repository.findById(id);
        if(opt.isEmpty())
            throw new NotFoundException("ID inexistente");
        repository.deleteById(id);
        return mapper.convertValue(opt.get(), TurnoDto.class);
    }

    @Override
    public List<TurnoDtoRespuestaSemanal> semanales(){
        List<TurnoDtoRespuestaSemanal> t1 = new ArrayList<>();
        for (Turno t  : repository.semanales(LocalDateTime.now().plusDays(7)).get() ) {
            t1.add(new TurnoDtoRespuestaSemanal(t));
        }
        return t1;
    }

    public boolean validacionFechaOdontologo(Integer id, LocalDateTime fecha) throws InvalidInputException, NotFoundException {
        odontologoService.buscar(id);
        return repository.buscarPorHorarioOdontologo(id, fecha).isPresent();
    }
    public boolean validacionFechaPaciente(Integer id, LocalDateTime fecha) throws InvalidInputException, NotFoundException {
        pacienteService.buscar(id);
        return repository.buscarPorHorarioPaciente(id, fecha).isPresent();
    }




}
