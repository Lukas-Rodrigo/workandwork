package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.domain.dtos.TecnicoDTO;
import com.lucasteixeira.workandwork.repositories.TecnicoRepository;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico criarTecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setCpf(null);
        Tecnico novoTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(novoTecnico);
    }
}
