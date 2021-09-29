package com.dh.clinica.service;

import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.TurnoDtoRespuestaSemanal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TurnoService extends CrudService<TurnoDto> {

    List<TurnoDtoRespuestaSemanal> buscarTurnosProximaSemana();
}
