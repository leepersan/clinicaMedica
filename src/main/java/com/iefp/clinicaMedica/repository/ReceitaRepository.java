package com.iefp.clinicaMedica.repository;

import com.iefp.clinicaMedica.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
