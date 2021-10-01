package com.dh.clinica.service;


import com.dh.clinica.exceptions.InvalidInputException;
import com.dh.clinica.exceptions.NotFoundException;

import java.util.List;

public interface CrudService<T> {
    T guardar(T t) throws InvalidInputException, NotFoundException;
    T buscar(Integer id) throws InvalidInputException, NotFoundException;
    T eliminar(Integer id) throws NotFoundException, InvalidInputException;
    List<T> buscarTodos();
    T actualizar(T t) throws InvalidInputException, NotFoundException;
}
