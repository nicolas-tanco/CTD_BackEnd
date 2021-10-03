package com.dh.clinica.persistence.repositories;

import com.dh.clinica.persistence.entities.Odontologo;
import com.dh.clinica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("SELECT t FROM Turno t WHERE t.odontologo.id=?1 AND t.fecha=?2")
    Optional<Turno> buscarPorHorarioOdontologo(Integer id, LocalDateTime fecha);
    @Query("SELECT t FROM Turno t WHERE t.paciente.id=?1 AND t.fecha=?2")
    Optional<Turno> buscarPorHorarioPaciente(Integer id, LocalDateTime fecha);
    @Query("SELECT t FROM Turno t WHERE t.fecha>?1 AND t.fecha<?2")
    List<Turno>semanales(LocalDateTime fecha, LocalDateTime fecha2);
}
