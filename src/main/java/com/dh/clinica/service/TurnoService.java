package com.dh.clinica.service;

import com.dh.clinica.dto.FechaDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.dto.TurnoDtoRespuestaSemanal;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TurnoService extends CrudService<TurnoDto> {

    List<TurnoDtoRespuestaSemanal> semanales(FechaDto f);
}
