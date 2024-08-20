package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Chamado;
import com.lucasteixeira.workandwork.repositories.ChamadoRepository;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamadoOptional = chamadoRepository.findById(id);
        return chamadoOptional.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado"));
    }
}
