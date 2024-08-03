package com.trabalho.ufc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_arbitro")
public class Arbitro extends Pessoa {

    @ManyToOne
    @JoinColumn(name = "luta_id")
    private Luta luta;
}
