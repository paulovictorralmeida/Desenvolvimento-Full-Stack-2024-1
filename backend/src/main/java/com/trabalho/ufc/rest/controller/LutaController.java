package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.entity.Luta;
import com.trabalho.ufc.domain.service.LutaService;
import com.trabalho.ufc.domain.service.ModelMapperService;
import com.trabalho.ufc.rest.controller.dto.luta.LutaCadastrarDto;
import com.trabalho.ufc.rest.controller.dto.luta.LutaListarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lutas")
public class LutaController {

    private final LutaService lutaService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public LutaController(LutaService lutaService, ModelMapperService modelMapperService){
        this.lutaService = lutaService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid LutaCadastrarDto dto){
        Luta luta = new Luta();
        luta.setPremio(dto.getPremio());
        luta.setJuiz(dto.getJuiz());
        luta.setVencedor(dto.getVencedor());
        List<Atleta> atletas = dto.getAtletasIds().stream()
                .map(Atleta::new)
                .collect(Collectors.toList());
        luta.setAtletas(atletas);
        luta.setId(0L);

        lutaService.cadastrar(luta);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Map> listar(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int itemsPerPage){
        return ResponseEntity.ok(modelMapperService.toPage(LutaListarDto.class, lutaService.listar(page, itemsPerPage)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid LutaCadastrarDto dto, @PathVariable Long id){
        Luta luta = modelMapperService.toObject(Luta.class, dto);
        luta.setId(id);
        lutaService.editar(luta);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        lutaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
