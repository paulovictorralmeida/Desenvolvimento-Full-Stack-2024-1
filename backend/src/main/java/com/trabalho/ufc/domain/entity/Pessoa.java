package com.trabalho.ufc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String nome;

    @NotNull
    Long idade;

    @Column(length = 11)
    @NotBlank
    String cpf;

    @Column(length = 7)
    @NotBlank
    String rg;

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(Long id, String nome, Long idade, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.rg = rg;
    }
}
