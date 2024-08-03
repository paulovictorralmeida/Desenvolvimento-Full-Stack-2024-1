package com.trabalho.ufc.domain.repository;

import com.trabalho.ufc.domain.entity.Juiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuizRepository extends JpaRepository<Juiz, Long> {
}
