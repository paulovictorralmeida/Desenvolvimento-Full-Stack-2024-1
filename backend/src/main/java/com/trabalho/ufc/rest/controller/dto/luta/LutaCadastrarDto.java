package com.trabalho.ufc.rest.controller.dto.luta;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.entity.Juiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LutaCadastrarDto {
    private Double premio;
    private Juiz juiz;
    private Atleta vencedor;
    private List<Long> atletasIds;
}
