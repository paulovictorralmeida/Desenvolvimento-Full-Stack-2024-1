package com.trabalho.ufc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_atleta")
public class Atleta extends Pessoa {

    @NotNull
    private Double peso;

    @ManyToOne
    @JoinColumn
    private Faixa faixa;

    @ManyToMany(mappedBy = "atletas")
    private List<Luta> lutas = new ArrayList<>();

    @OneToMany(mappedBy = "vencedor")
    private List<Luta> vitorias = new ArrayList<>();

    public Atleta(){
        super();
    }

    @Builder(toBuilder = true)
    public Atleta(Long id){
        super(id);
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
