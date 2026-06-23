package com.iefp.clinicaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especialidade;

    //Composição
    @OneToOne
    @JoinColumn(name="utilizador_id", unique = true)
    private Utilizador utilizador;

}
