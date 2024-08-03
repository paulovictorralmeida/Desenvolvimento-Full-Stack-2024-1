package com.trabalho.ufc.rest.controller.dto.juiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JuizListarDto {
    private Long id;
    private String nome;
    private Long idade;
    private String cpf;
    private String rg;
}
