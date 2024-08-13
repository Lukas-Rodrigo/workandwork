package com.lucasteixeira.workandwork.repositories;

import com.lucasteixeira.workandwork.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
