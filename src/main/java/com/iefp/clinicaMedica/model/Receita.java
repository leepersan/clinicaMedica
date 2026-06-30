package com.iefp.clinicaMedica.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicamento;
    private String dosagem;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @Column(length = 1000)
    private String instrucoes;
}
