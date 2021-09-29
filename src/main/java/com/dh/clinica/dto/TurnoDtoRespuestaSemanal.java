package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Turno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TurnoDtoRespuestaSemanal {

    private int dia;
    private int hora;
    private String odontologoApellido;
    private String odontologoNombre;
    private String pacienteNombre;
    private String pacienteApellido;

    public TurnoDtoRespuestaSemanal(){}

    public TurnoDtoRespuestaSemanal(TurnoDto t){
        dia = t.getFecha().getDayOfMonth();
        hora = t.getFecha().getHour();
        odontologoApellido = t.getOdontologo().getApellido();
        odontologoNombre = t.getOdontologo().getNombre();
        pacienteApellido =t.getPaciente().getApellido();
        pacienteNombre = t.getPaciente().getNombre();
    }
}
