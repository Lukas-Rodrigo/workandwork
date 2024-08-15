package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.repositories.TecnicoRepository;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
}
