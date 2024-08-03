package com.trabalho.ufc.rest.controller;

import com.trabalho.ufc.domain.entity.Faixa;
import com.trabalho.ufc.domain.entity.Juiz;
import com.trabalho.ufc.domain.service.JuizService;
import com.trabalho.ufc.domain.service.ModelMapperService;
import com.trabalho.ufc.rest.controller.dto.juiz.JuizCadastrarDto;
import com.trabalho.ufc.rest.controller.dto.juiz.JuizListarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/juizes")
public class JuizController {

    private final JuizService juizService;
    private final ModelMapperService modelMapperService;

    public JuizController(JuizService juizService, ModelMapperService modelMapperService) {
        this.juizService = juizService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping
    public ResponseEntity<Juiz> cadastrar(@RequestBody @Valid JuizCadastrarDto dto){
        Juiz juiz = modelMapperService.toObject(Juiz.class, dto);
        juiz.setId(0L);

        Juiz savedJuiz = juizService.cadastrar(juiz);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedJuiz.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedJuiz);
    }

    @GetMapping
    public ResponseEntity<Map> listar(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int itemsPerPage){
        return ResponseEntity.ok(modelMapperService.toPage(JuizListarDto.class, juizService.listar(page, itemsPerPage)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juiz> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(juizService.pegarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@RequestBody @Valid JuizCadastrarDto dto, @PathVariable Long id){
        Juiz juiz = modelMapperService.toObject(Juiz.class, dto);
        juiz.setId(id);
        juizService.editar(juiz);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        juizService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
