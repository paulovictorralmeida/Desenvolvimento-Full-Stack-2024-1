package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtletaService {

    private final AtletaRepository atletaRepository;

    @Autowired
    public AtletaService(AtletaRepository atletaRepository){
        this.atletaRepository = atletaRepository;
    }
    public void cadastrar(Atleta atleta) {
        atleta.setId(0L);
        atletaRepository.save(atleta);
    }
}
