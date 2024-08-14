package com.lucasteixeira.workandwork.repositories;

import com.lucasteixeira.workandwork.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
