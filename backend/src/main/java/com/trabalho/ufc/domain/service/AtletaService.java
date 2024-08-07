package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Atleta;
import com.trabalho.ufc.domain.repository.AtletaRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtletaService {

    private final AtletaRepository atletaRepository;
    private final FaixaService faixaService;

    @Autowired
    public AtletaService(AtletaRepository atletaRepository, FaixaService faixaService){
        this.atletaRepository = atletaRepository;
        this.faixaService = faixaService;
    }

    public Atleta cadastrar(Atleta atleta) {
        atleta.setId(0L);
        if(StringUtil.isNullOrEmpty(atleta.getNome()) ||
            StringUtil.isNullOrEmpty(atleta.getIdade()) ||
            StringUtil.isNullOrEmpty(atleta.getCpf()) ||
            StringUtil.isNullOrEmpty(atleta.getRg()) ||
             StringUtil.isNullOrEmpty(atleta.getPeso())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }

        if(atleta.getCpf().length() > 11){
            throw UfcException.badRequest("003", "O tamanho máximo para cpf é de 11 digitos.");
        }

        if(atleta.getRg().length() > 7){
            throw UfcException.badRequest("003", "O tamanho máximo para rg é de 7 digitos.");
        }

        atleta.setFaixa(faixaService.buscarFaixaPorPeso(atleta.getPeso()));
        return atletaRepository.save(atleta);
    }

    public Atleta pegarPorId(Long id){
        Optional<Atleta> atleta = atletaRepository.findById(id);
        if(atleta.isEmpty()){
            throw UfcException.notFound("", "Atleta com o id " + id + " não encontrado.");
        }
        return atleta.get();
    }

    public void editar(Atleta atleta) {
        this.pegarPorId(atleta.getId());
        atletaRepository.save(atleta);
    }

    public void deletar(Long id) {
        this.pegarPorId(id);
        atletaRepository.deleteById(id);
    }

    public Page<Atleta> listar(int page, int itemsPerPage) {
        return atletaRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(Sort.Direction.DESC, "nome")));
    }
}
