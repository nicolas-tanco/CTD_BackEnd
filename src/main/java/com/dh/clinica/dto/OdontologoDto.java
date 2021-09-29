package com.dh.clinica.dto;

import com.dh.clinica.persistence.entities.Odontologo;

public class OdontologoDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(Odontologo o){
        id= o.getId();
        nombre= o.getNombre();
        apellido= o.getApellido();
        matricula= o.getMatricula();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Odontologo toEntity(){
        Odontologo o = new Odontologo();
        o.setApellido(apellido);
        o.setId(id);
        o.setMatricula(matricula);
        o.setNombre(nombre);
        return o;
    }
}
