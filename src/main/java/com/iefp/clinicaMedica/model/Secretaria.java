package com.iefp.clinicaMedica.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Secretaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //Composição

    @OneToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;
}
