package com.trabalho.ufc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_faixa")
public class Faixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "peso_minimo")
    private Integer pesoMinimo;

    @Column(name = "peso_maximo")
    private Integer pesoMaximo;
    @Column(length = 3000)
    private String descricao;
}
