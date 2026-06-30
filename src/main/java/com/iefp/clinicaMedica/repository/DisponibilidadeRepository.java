package com.iefp.clinicaMedica.repository;

import com.iefp.clinicaMedica.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {


    //disponibilidade livre por especialidade
    List<Disponibilidade> findByMedico_EspecialidadeIgnoreCaseAndOcupadaFalseOrderByDataAscHoraInicioAsc(String especialidade);

    //disponibilidade médico
    List<Disponibilidade> findByMedico_IdOrderByDataAscHoraInicioAsc(Long medicoId);

    //Verificar se já existe disponibilidade igual para o médico
    Boolean existsByMedico_IdAndDataAndHoraInicioAndHoraFim(Long medicoId,
                                                            LocalDate data,
                                                            LocalTime horaInicio,
                                                            LocalTime horaFim);


}
