package com.lucasteixeira.workandwork.repositories;

import com.lucasteixeira.workandwork.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteaRepository extends JpaRepository<Cliente, Integer> {
}
