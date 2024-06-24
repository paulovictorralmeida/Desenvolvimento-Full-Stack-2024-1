package com.trabalho.ufc.domain.repository;

import com.trabalho.ufc.domain.entity.Atleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {

}
