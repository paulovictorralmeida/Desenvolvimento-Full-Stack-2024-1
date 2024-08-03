package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Arbitro;
import com.trabalho.ufc.domain.service.ArbitroService;
import com.trabalho.ufc.domain.service.ModelMapperService;
import com.trabalho.ufc.rest.controller.dto.arbitro.ArbitroCadastrarDto;
import com.trabalho.ufc.rest.controller.dto.arbitro.ArbitroListarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/arbitros")
public class ArbitroController {

    private final ArbitroService arbitroService;
    private final ModelMapperService modelMapperService;

    public ArbitroController(ArbitroService arbitroService, ModelMapperService modelMapperService) {
        this.arbitroService = arbitroService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    public ResponseEntity<Arbitro> cadastrar(@RequestBody @Valid ArbitroCadastrarDto dto){
        Arbitro arbitro = modelMapperService.toObject(Arbitro.class, dto);
        arbitro.setId(0L);

        Arbitro savedArbitro = arbitroService.cadastrar(arbitro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArbitro.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedArbitro);
    }

    @GetMapping
    public ResponseEntity<Map> listar(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int itemsPerPage){
        return ResponseEntity.ok(modelMapperService.toPage(ArbitroListarDto.class, arbitroService.listar(page, itemsPerPage)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid ArbitroCadastrarDto dto, @PathVariable Long id){
        Arbitro arbitro = modelMapperService.toObject(Arbitro.class, dto);
        arbitro.setId(id);
        arbitroService.editar(arbitro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        arbitroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
