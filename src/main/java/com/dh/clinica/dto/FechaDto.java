package com.dh.clinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FechaDto {
    private Integer dia;
    private Integer mes;
    private Integer anio;

    public FechaDto(Integer dia, Integer mes, Integer anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public FechaDto() {
    }
}
