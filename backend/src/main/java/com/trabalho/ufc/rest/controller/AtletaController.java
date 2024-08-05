package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.entity.Faixa;
import com.trabalho.ufc.domain.service.AtletaService;
import com.trabalho.ufc.domain.service.ModelMapperService;
import com.trabalho.ufc.rest.controller.dto.atleta.AtletaCadastrarDto;
import com.trabalho.ufc.rest.controller.dto.atleta.AtletaListarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/atletas")
public class AtletaController {

    private final AtletaService atletaService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public AtletaController(AtletaService atletaService, ModelMapperService modelMapperService){
        this.atletaService = atletaService;
        this.modelMapperService = modelMapperService;
    }


    @PostMapping
    public ResponseEntity<Atleta> cadastrar(@RequestBody @Valid AtletaCadastrarDto dto){
        Atleta atleta = modelMapperService.toObject(Atleta.class, dto);
        atleta.setId(0L);

        Atleta savedAtleta = atletaService.cadastrar(atleta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAtleta.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedAtleta);
    }

    @GetMapping
    public ResponseEntity<Map> listar(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int itemsPerPage){
        return ResponseEntity.ok(modelMapperService.toPage(AtletaListarDto.class, atletaService.listar(page, itemsPerPage)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid AtletaCadastrarDto dto, @PathVariable Long id){
        Atleta atleta = modelMapperService.toObject(Atleta.class, dto);
        atleta.setId(id);
        atletaService.editar(atleta);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        atletaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtletaListarDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(modelMapperService.toObject(AtletaListarDto.class,atletaService.pegarPorId(id)));
    }
}
