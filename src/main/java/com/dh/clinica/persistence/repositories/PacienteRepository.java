package com.dh.clinica.persistence.repositories;

import com.dh.clinica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Para la relación Paciente-Domicilio se dejó una relación unidireccional para evitar el ciclo infinito
 * al momento de hacer las consultas.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
