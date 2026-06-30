package com.iefp.clinicaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoExame;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 1000)
    private String resultado;


    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;
}

