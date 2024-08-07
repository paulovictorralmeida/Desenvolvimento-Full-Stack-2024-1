package com.trabalho.ufc.rest.controller.dto.atleta;

import com.trabalho.ufc.domain.entity.Faixa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtletaListarDto {
    private Long id;
    private String nome;
    private Long idade;
    private String cpf;
    private String rg;
    private Double peso;
    private Faixa faixa;
}
