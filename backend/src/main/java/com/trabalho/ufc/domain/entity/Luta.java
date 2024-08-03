package com.trabalho.ufc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_luta")
public class Luta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double premio;

    @ManyToMany
    @JoinTable(
            name = "atleta_luta",
            joinColumns = @JoinColumn(name = "luta_id"),
            inverseJoinColumns = @JoinColumn(name = "atleta_id")
    )
    private List<Atleta> atletas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vencedor_id")
    private Atleta vencedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "juiz_id", referencedColumnName = "id")
    private Juiz juiz;

    @OneToMany(mappedBy = "luta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arbitro> arbitros = new ArrayList<>();

}
