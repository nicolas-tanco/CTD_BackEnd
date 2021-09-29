package com.dh.clinica.persistence.repositories;

import com.dh.clinica.persistence.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);
}