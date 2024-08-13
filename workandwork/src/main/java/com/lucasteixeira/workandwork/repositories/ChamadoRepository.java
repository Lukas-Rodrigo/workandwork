package com.lucasteixeira.workandwork.repositories;

import com.lucasteixeira.workandwork.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
