package com.iefp.clinicaMedica.repository;

import com.iefp.clinicaMedica.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    Boolean existsByEmail(String email);

    Optional<Utilizador>findByEmailAndSenha(String email,  String senha);
}

