package com.dh.clinica.persistence.repositories;

import com.dh.clinica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
