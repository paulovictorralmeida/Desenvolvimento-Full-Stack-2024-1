package com.trabalho.ufc.domain.repository;

import com.trabalho.ufc.domain.entity.Arbitro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbitroRepository extends JpaRepository<Arbitro, Long> {
}
