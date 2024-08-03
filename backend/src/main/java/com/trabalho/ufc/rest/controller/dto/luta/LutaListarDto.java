package com.trabalho.ufc.rest.controller.dto.luta;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.entity.Juiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LutaListarDto {
    private Long id;
    private Double premio;
    private Juiz juiz;
    private Atleta vencedor;
}
