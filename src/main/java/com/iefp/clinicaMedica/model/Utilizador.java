package com.iefp.clinicaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor

    public class Utilizador {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

       @Column(unique = true)
        private String email;

       private String senha;
       private String perfil;

       private LocalDate dataNascimento;
       private String telefone;
       private String endereco;

       public Integer getIdade(){
           if(dataNascimento==null){
               return 0;
           }
           return Period.between(dataNascimento, LocalDate.now()).getYears();
       }

    }

