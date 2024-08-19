package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Pessoa;
import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.domain.dtos.TecnicoDTO;
import com.lucasteixeira.workandwork.repositories.PessoaRepository;
import com.lucasteixeira.workandwork.repositories.TecnicoRepository;
import com.lucasteixeira.workandwork.services.exception.DataIntegrityViolationException;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

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
        tecnicoDTO.setId(null);
        validaCpfEEmail(tecnicoDTO);
        Tecnico novoTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(novoTecnico);
    }

    public Tecnico update(Integer id, TecnicoDTO dadosTecnico) {
        dadosTecnico.setId(id);
        Tecnico tecnicoAntigo = findById(id);
        validaCpfEEmail(dadosTecnico);
        tecnicoAntigo = new Tecnico(dadosTecnico);
        return tecnicoRepository.save(tecnicoAntigo);
    }

    private void validaCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }

    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (tecnico.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui orders de serviço e não pode ser deletado");
        }
        tecnicoRepository.delete(tecnico);

    }
}
