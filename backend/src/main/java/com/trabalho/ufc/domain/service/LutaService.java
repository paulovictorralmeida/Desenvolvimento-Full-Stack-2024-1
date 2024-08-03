package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.entity.Juiz;
import com.trabalho.ufc.domain.entity.Luta;
import com.trabalho.ufc.domain.repository.LutaRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LutaService {
    private final LutaRepository lutaRepository;
    private final JuizService juizService;
    private final AtletaService atletaService;

    public LutaService(LutaRepository lutaRepository, JuizService juizService, AtletaService atletaService) {
        this.lutaRepository = lutaRepository;
        this.juizService = juizService;
        this.atletaService = atletaService;
    }

    public Luta cadastrar(Luta luta) {
        luta.setId(0L);
        if(StringUtil.isNullOrEmpty(luta.getPremio())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }

        Juiz juiz = juizService.pegarPorId(luta.getJuiz().getId());
        luta.setJuiz(juiz);

        List<Atleta> atletas = new ArrayList<>();

        luta.getAtletas().forEach(atleta -> {
            atletas.add(new Atleta(atleta.getId()));
        });

        luta.setAtletas(atletas);

        return lutaRepository.save(luta);
    }

    public Luta pegarPorId(Long id){
        Optional<Luta> luta = lutaRepository.findById(id);
        if(luta.isEmpty()){
            throw UfcException.notFound("", "Luta com o id " + id + " não encontrado.");
        }
        return luta.get();
    }

    public void editar(Luta luta) {
        this.pegarPorId(luta.getId());
        lutaRepository.save(luta);
    }

    public void deletar(Long id) {
        this.pegarPorId(id);
        lutaRepository.deleteById(id);
    }

    public Page<Luta> listar(int page, int itemsPerPage) {
        return lutaRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(Sort.Direction.DESC, "nome")));
    }
}
