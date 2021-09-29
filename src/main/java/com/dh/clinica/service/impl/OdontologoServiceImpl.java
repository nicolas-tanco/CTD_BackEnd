package com.dh.clinica.service.impl;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;
import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.repositories.OdontologoRepository;
import com.dh.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    OdontologoRepository repository;



    public OdontologoDto guardar(OdontologoDto o) throws InvalidInputException {

        if(o.getId()!=null && repository.findById(o.getId()).isPresent())
            throw new InvalidInputException("El odontologo ya existe");
        o.setId(repository.save(o.toEntity()).getId());
        return o;
    }

    public OdontologoDto buscar(Integer id) throws InvalidInputException, NotFoundException {
        if(id==null)
            throw new InvalidInputException("El id no puede ser null");
       Optional<Odontologo> o = repository.findById(id);
        if(o.isEmpty())
            throw new NotFoundException("El id "+id+" no existe.");
        return new OdontologoDto(o.get());
    }

    public List<OdontologoDto> buscarTodos(){

        List<OdontologoDto> ods = new ArrayList<>();
        for (Odontologo o: repository.findAll()) {
            ods.add(new OdontologoDto(o));
        }
        return ods;
    }

    public OdontologoDto actualizar(OdontologoDto o) throws InvalidInputException {

        Optional <Odontologo> o1 = repository.findById(o.getId());
        if(o1.isEmpty())
            throw new InvalidInputException("El odontologo no existe");
        if(o.getApellido()!=null)
            o1.get().setApellido(o.getApellido());
        if(o.getNombre()!=null)
            o1.get().setNombre(o.getNombre());
        if(o.getMatricula()!=null)
            o1.get().setMatricula(o.getMatricula());
        repository.save(o1.get());
        return new OdontologoDto(o1.get());
    }

    public OdontologoDto eliminar(Integer id) throws NotFoundException, InvalidInputException {
        Optional <Odontologo> o = repository.findById(id);
        if(id == null)
            throw new InvalidInputException("El id no puede ser null");
        if(o.isEmpty())
            throw new NotFoundException("No existe el odontologo con el id "+id);
        repository.deleteById(id);
        return new OdontologoDto(o.get());
    }
}