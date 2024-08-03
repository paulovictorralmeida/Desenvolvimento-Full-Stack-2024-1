package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Faixa;
import com.trabalho.ufc.domain.repository.FaixaRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FaixaService {
    private final FaixaRepository faixaRepository;

    @Autowired
    public FaixaService(FaixaRepository faixaRepository){
        this.faixaRepository = faixaRepository;
    }

    public Faixa cadastrar(Faixa faixa) {
        faixa.setId(0L);
        if(StringUtil.isNullOrEmpty(faixa.getPesoMinimo()) ||
                StringUtil.isNullOrEmpty(faixa.getPesoMaximo())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }

        return faixaRepository.save(faixa);
    }

    public Faixa pegarPorId(Integer id){
        Optional<Faixa> faixa = faixaRepository.findById(id);
        if(faixa.isEmpty()){
            throw UfcException.notFound("", "Faixa com o id " + id + " não encontrado.");
        }
        return faixa.get();
    }

    public void editar(Faixa faixa) {
        this.pegarPorId(Integer.parseInt(String.valueOf(faixa.getId())));
        faixaRepository.save(faixa);
    }

    public void deletar(Long id) {
        this.pegarPorId(Integer.parseInt(String.valueOf(id)));
        faixaRepository.deleteById(Integer.parseInt(String.valueOf(id)));
    }

    public Page<Faixa> listar(int page, int itemsPerPage) {
        return faixaRepository.findAll(PageRequest.of(page, itemsPerPage));
    }

    Faixa buscarFaixaPorPeso(double peso){
        int pesoInt = (int) peso;
        Faixa faixa = faixaRepository.findByPeso(pesoInt);
        if(faixa == null){
            throw UfcException.notFound("", "Faixa com o peso " + pesoInt + " não encontrado.");
        }

        return faixa;
    }
}
