package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.service.AtletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atleta")
public class AtletaController {

    private final AtletaService atletaService;

    @Autowired
    public AtletaController(AtletaService atletaService){
        this.atletaService = atletaService;
    }

    @PostMapping
    public void cadastrar(@RequestBody Atleta atleta){
        atletaService.cadastrar(atleta);
    }
}
