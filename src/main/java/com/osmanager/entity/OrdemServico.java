package com.osmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroOS;

    private LocalDate dataEntrada;

    private LocalDate dataSaida;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    private String observacoes;

    private String laudoTecnico;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Equipamento equipamento;

    @ManyToOne
    private Usuario tecnico;
}