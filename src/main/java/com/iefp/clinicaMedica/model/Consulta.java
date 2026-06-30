package com.iefp.clinicaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.internal.Abstract;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disponibilidade_id")
    private Disponibilidade disponibilidade;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "secretaria_id")
    private Secretaria secretaria;

    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String estado;
}
