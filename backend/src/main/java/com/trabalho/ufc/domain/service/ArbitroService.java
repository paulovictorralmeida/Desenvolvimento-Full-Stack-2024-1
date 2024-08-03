package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Arbitro;
import com.trabalho.ufc.domain.repository.ArbitroRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArbitroService {

    private final ArbitroRepository arbitroRepository;

    public ArbitroService(ArbitroRepository arbitroRepository) {
        this.arbitroRepository = arbitroRepository;
    }

    public Arbitro cadastrar(Arbitro arbitro) {
        arbitro.setId(0L);
        if(StringUtil.isNullOrEmpty(arbitro.getNome()) ||
                StringUtil.isNullOrEmpty(arbitro.getIdade()) ||
                StringUtil.isNullOrEmpty(arbitro.getCpf()) ||
                StringUtil.isNullOrEmpty(arbitro.getRg())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }

        if(arbitro.getCpf().length() > 11){
            throw UfcException.badRequest("003", "O tamanho máximo para cpf é de 11 digitos.");
        }

        if(arbitro.getRg().length() > 7){
            throw UfcException.badRequest("003", "O tamanho máximo para rg é de 7 digitos.");
        }
        return arbitroRepository.save(arbitro);
    }

    public Arbitro pegarPorId(Long id){
        Optional<Arbitro> atleta = arbitroRepository.findById(id);
        if(atleta.isEmpty()){
            throw UfcException.notFound("", "Arbitro com o id " + id + " não encontrado.");
        }
        return atleta.get();
    }

    public void editar(Arbitro arbitro) {
        this.pegarPorId(arbitro.getId());
        arbitroRepository.save(arbitro);
    }

    public void deletar(Long id) {
        this.pegarPorId(id);
        arbitroRepository.deleteById(id);
    }

    public Page<Arbitro> listar(int page, int itemsPerPage) {
        return arbitroRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(Sort.Direction.DESC, "nome")));
    }
}
