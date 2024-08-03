package com.trabalho.ufc.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_juiz")
public class Juiz extends Pessoa{

    @OneToOne(mappedBy = "juiz")
    private Luta luta;
}
