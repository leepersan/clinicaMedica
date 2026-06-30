package com.iefp.clinicaMedica.repository;

import com.iefp.clinicaMedica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("Select DISTINCT m.especialidade FROM Medico m WHERE m.especialidade IS NOT NULL")
    List<String>listarEspecialidades();
}
