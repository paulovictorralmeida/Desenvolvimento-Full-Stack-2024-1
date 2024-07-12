package com.trabalho.ufc.rest.controller.dto.atleta;

import com.trabalho.ufc.domain.entity.Faixa;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class AtletaCreateDto {
    private String nome;
    private Long idade;
    @Size(max = 11, message = "O tamanho máximo para cpf é de 11 digitos.")
    private String cpf;
    @Size(max = 7, message = "O tamanho máximo para rg é de 7 digitos.")
    private String rg;
    private Double peso;
    private Faixa faixa;
}
