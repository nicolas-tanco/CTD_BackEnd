package com.dh.clinica.service.impl;

import com.dh.clinica.config.SpringConfig;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.TurnoDtoRespuestaSemanal;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Paciente;
import com.dh.clinica.persistence.entities.Turno;
import com.dh.clinica.persistence.repositories.TurnoRepository;
import com.dh.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private SpringConfig springConfig;

    @Override
    public TurnoDto guardar(TurnoDto t) {
         return null;
    }

    @Override
    public TurnoDto buscar(Integer id) {
        return null;
    }

    @Override
    public List<TurnoDto> buscarTodos() {
        return repository.findAll().stream().map(element->springConfig.getModelMapper().map(element,TurnoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TurnoDto actualizar(TurnoDto turno) {
        return null;
    }

    public TurnoDto eliminar (Integer id){
        return null;
    }

    @Override
    public List<TurnoDtoRespuestaSemanal> buscarTurnosProximaSemana(){
        LocalDateTime proximaSemana = LocalDateTime.now().plusDays(7);

        List<TurnoDto> turnos = buscarTodos().stream().filter(turno->turno.getFecha().isBefore(proximaSemana)).collect(Collectors.toList());
        List<TurnoDtoRespuestaSemanal> turnosDto= new ArrayList<>();
        for (TurnoDto t: turnos) {
            TurnoDtoRespuestaSemanal t1 = new TurnoDtoRespuestaSemanal(t);
            turnosDto.add(t1);
        }
        return turnosDto;
    }

    public boolean validacionFechaOdontologo(Integer id, LocalDateTime fecha){
        return repository.buscarPorHorarioOdontologo(id, fecha).isPresent();
    }
    public boolean validacionFechaPaciente(Integer id, LocalDateTime fecha){
        return repository.buscarPorHorarioPaciente(id, fecha).isPresent();
    }


}
