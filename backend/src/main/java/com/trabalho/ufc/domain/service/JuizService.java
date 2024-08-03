package com.trabalho.ufc.domain.service;

import com.trabalho.ufc.domain.entity.Arbitro;
import com.trabalho.ufc.domain.entity.Juiz;
import com.trabalho.ufc.domain.repository.JuizRepository;
import com.trabalho.ufc.infrastructure.exception.UfcException;
import com.trabalho.ufc.utils.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuizService {

    private final JuizRepository juizRepository;

    public JuizService(JuizRepository juizRepository) {
        this.juizRepository = juizRepository;
    }

    public Juiz cadastrar(Juiz juiz) {
        juiz.setId(0L);
        if(StringUtil.isNullOrEmpty(juiz.getNome()) ||
                StringUtil.isNullOrEmpty(juiz.getIdade()) ||
                StringUtil.isNullOrEmpty(juiz.getCpf()) ||
                StringUtil.isNullOrEmpty(juiz.getRg())) {
            throw UfcException.badRequest("001", "Campos obrigatórios não preenchidos.");
        }

        if(juiz.getCpf().length() > 11){
            throw UfcException.badRequest("003", "O tamanho máximo para cpf é de 11 digitos.");
        }

        if(juiz.getRg().length() > 7){
            throw UfcException.badRequest("003", "O tamanho máximo para rg é de 7 digitos.");
        }
        return juizRepository.save(juiz);
    }

    public Juiz pegarPorId(Long id){
        Optional<Juiz> juiz = juizRepository.findById(id);
        if(juiz.isEmpty()){
            throw UfcException.notFound("", "Juiz com o id " + id + " não encontrado.");
        }
        return juiz.get();
    }

    public void editar(Juiz juiz) {
        this.pegarPorId(juiz.getId());
        juizRepository.save(juiz);
    }

    public void deletar(Long id) {
        this.pegarPorId(id);
        juizRepository.deleteById(id);
    }

    public Page<Juiz> listar(int page, int itemsPerPage) {
        return juizRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(Sort.Direction.DESC, "nome")));
    }
}
