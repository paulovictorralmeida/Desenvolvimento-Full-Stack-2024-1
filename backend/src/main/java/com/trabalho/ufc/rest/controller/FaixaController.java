package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Faixa;
import com.trabalho.ufc.domain.service.FaixaService;
import com.trabalho.ufc.domain.service.ModelMapperService;
import com.trabalho.ufc.rest.controller.dto.faixa.FaixaCadastrarDto;
import com.trabalho.ufc.rest.controller.dto.faixa.FaixaListarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/faixas")
public class FaixaController {

    private final FaixaService faixaService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public FaixaController(FaixaService faixaService, ModelMapperService modelMapperService){
        this.faixaService = faixaService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    public ResponseEntity<Faixa> cadastrar(@RequestBody @Valid FaixaCadastrarDto dto){
        Faixa faixa = modelMapperService.toObject(Faixa.class, dto);
        faixa.setId(0L);

        Faixa savedFaixa = faixaService.cadastrar(faixa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFaixa.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedFaixa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faixa> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(faixaService.pegarPorId(Integer.parseInt(String.valueOf(id))));
    }

    @GetMapping
    public ResponseEntity<Map> listar(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int itemsPerPage){
        return ResponseEntity.ok(modelMapperService.toPage(FaixaListarDto.class, faixaService.listar(page, itemsPerPage)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid FaixaCadastrarDto dto, @PathVariable Long id){
        Faixa faixa = modelMapperService.toObject(Faixa.class, dto);
        faixa.setId(id);
        faixaService.editar(faixa);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        faixaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
