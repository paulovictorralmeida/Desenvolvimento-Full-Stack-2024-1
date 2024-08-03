package com.trabalho.ufc.rest.controller.dto.faixa;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class FaixaCadastrarDto {
    private Integer pesoMinimo;
    private Integer pesoMaximo;
    @Size(max = 3000, message = "O tamanho máximo para a descrição é 3000 caracteres.")
    private String descricao;
}
