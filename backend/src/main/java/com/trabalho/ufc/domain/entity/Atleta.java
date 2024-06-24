package com.trabalho.ufc.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "tb_atleta")
public class Atleta extends Pessoa {

    @NotNull
    private Double peso;

    @ManyToOne
    @JoinColumn
    private Faixa faixa;

    public Atleta(){
        super();
    }

    @Builder
    public Atleta(Double peso,
                  Faixa faixa,
                  Long id,
                  String nome,
                  Long idade,
                  String cpf,
                  String rg) {
        super(id, nome, idade, cpf, rg);
        this.peso = peso;
        this.faixa = faixa;
    }
}
