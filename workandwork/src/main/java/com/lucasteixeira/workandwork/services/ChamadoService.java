package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Chamado;
import com.lucasteixeira.workandwork.domain.Cliente;
import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.domain.dtos.ChamadoDTO;
import com.lucasteixeira.workandwork.repositories.ChamadoRepository;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamadoOptional = chamadoRepository.findById(id);
        return chamadoOptional.orElseThrow(() -> new ResourceNotFoundException("Chamado não encontrado"));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(null);
        return chamadoRepository.save(novoChamado(chamadoDTO));

    }

    private Chamado novoChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnicoId = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente clienteId = clienteService.findById(chamadoDTO.getCliente());
        return new Chamado(chamadoDTO, clienteId, tecnicoId);
    }
}
