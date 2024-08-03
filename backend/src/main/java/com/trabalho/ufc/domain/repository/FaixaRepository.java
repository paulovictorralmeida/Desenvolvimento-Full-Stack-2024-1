package com.trabalho.ufc.domain.repository;

import com.trabalho.ufc.domain.entity.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FaixaRepository extends JpaRepository<Faixa, Integer> {

    @Query("SELECT f FROM Faixa f WHERE :peso >= f.pesoMinimo AND :peso <= f.pesoMaximo")
    Faixa findByPeso(Integer peso);
}
