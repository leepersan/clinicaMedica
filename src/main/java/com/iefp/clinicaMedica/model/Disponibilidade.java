package com.iefp.clinicaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Disponibilidade {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @JoinColumn (name = "medico_id")
    private Medico medico;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    private Boolean ocupada;

}
