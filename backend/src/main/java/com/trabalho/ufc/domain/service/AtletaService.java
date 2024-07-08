package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.repository.AtletaRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtletaService {

    private final AtletaRepository atletaRepository;

    @Autowired
    public AtletaService(AtletaRepository atletaRepository){
        this.atletaRepository = atletaRepository;
    }
    public void cadastrar(Atleta atleta) {
        atleta.setId(0L);
        if(StringUtil.isNullOrEmpty(atleta.getNome()) ||
            StringUtil.isNullOrEmpty(atleta.getIdade()) ||
            StringUtil.isNullOrEmpty(atleta.getCpf()) ||
            StringUtil.isNullOrEmpty(atleta.getRg()) ||
             StringUtil.isNullOrEmpty(atleta.getPeso())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }
        atletaRepository.save(atleta);
    }
}
