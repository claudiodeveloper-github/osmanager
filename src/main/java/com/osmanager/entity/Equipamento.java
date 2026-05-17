package com.osmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String marca;

    private String modelo;

    private String numeroSerie;

    private String defeito;

    @ManyToOne
    private Cliente cliente;
}