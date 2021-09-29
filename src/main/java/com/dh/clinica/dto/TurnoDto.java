package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Turno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TurnoDto {

    private Integer id;
    private LocalDateTime fecha;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

    public TurnoDto(){}

    public TurnoDto(Turno t){
        this.id=t.getId();
        fecha=t.getFecha();
        paciente=new PacienteDto(t.getPaciente());
        odontologo= new OdontologoDto(t.getOdontologo());
    }

    public Turno toEntity(){
        Turno t = new Turno();
        t.setId(id);
        t.setFecha(fecha);
        t.setOdontologo(odontologo.toEntity());
        t.setPaciente(paciente.toEntity());
        return t;
    }

}
