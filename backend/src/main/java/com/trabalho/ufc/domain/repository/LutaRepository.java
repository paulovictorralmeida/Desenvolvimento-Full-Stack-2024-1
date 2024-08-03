package com.trabalho.ufc.domain.repository;

import com.trabalho.ufc.domain.entity.Luta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LutaRepository extends JpaRepository<Luta, Long> {
}
