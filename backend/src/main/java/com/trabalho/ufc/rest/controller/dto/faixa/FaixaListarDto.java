package com.trabalho.ufc.rest.controller.dto.faixa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaixaListarDto {
    private Long id;
    private Integer pesoMinimo;
    private Integer pesoMaximo;
    private String descricao;
}
