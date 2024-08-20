package com.lucasteixeira.workandwork.services;

import com.lucasteixeira.workandwork.domain.Pessoa;
import com.lucasteixeira.workandwork.domain.Cliente;
import com.lucasteixeira.workandwork.domain.dtos.ClienteDTO;
import com.lucasteixeira.workandwork.repositories.PessoaRepository;
import com.lucasteixeira.workandwork.repositories.ClienteRepository;
import com.lucasteixeira.workandwork.services.exception.DataIntegrityViolationException;
import com.lucasteixeira.workandwork.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = ClienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public List<Cliente> findAll() {
        return ClienteRepository.findAll();
    }

    public Cliente create(ClienteDTO ClienteDTO) {
        ClienteDTO.setId(null);
        validaCpfEEmail(ClienteDTO);
        Cliente novoCliente = new Cliente(ClienteDTO);
        return ClienteRepository.save(novoCliente);
    }

    public Cliente update(Integer id, ClienteDTO dadosCliente) {
        dadosCliente.setId(id);
        Cliente ClienteAntigo = findById(id);
        validaCpfEEmail(dadosCliente);
        ClienteAntigo = new Cliente(dadosCliente);
        return ClienteRepository.save(ClienteAntigo);
    }

    private void validaCpfEEmail(ClienteDTO ClienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(ClienteDTO.getCpf());

        if (pessoa.isPresent() && pessoa.get().getId() != ClienteDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        pessoa = pessoaRepository.findByEmail(ClienteDTO.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != ClienteDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado");
        }

    }

    public void delete(Integer id) {
        Cliente Cliente = findById(id);
        if (Cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui orders de serviço e não pode ser deletado");
        }
        ClienteRepository.delete(Cliente);

    }
}
