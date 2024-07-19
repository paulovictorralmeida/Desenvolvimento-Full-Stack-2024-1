package com.trabalho.ufc.rest.controller.dto.atleta;

import com.trabalho.ufc.domain.entity.Faixa;
import lombok.Data;

@Data
public class AtletaListarDto {
    private String nome;
    private Long idade;
    private String cpf;
    private String rg;
    private Double peso;
    private Faixa faixa;
}
