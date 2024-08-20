package com.lucasteixeira.workandwork.Resource;

import com.lucasteixeira.workandwork.domain.Cliente;
import com.lucasteixeira.workandwork.domain.dtos.ClienteDTO;
import com.lucasteixeira.workandwork.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService ClienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente Clienteobj = ClienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(Clienteobj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = ClienteService.findAll();
        List<ClienteDTO> listDto = list.stream().map(x -> new ClienteDTO(x)).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO ClienteDTO) {
        Cliente newCliente = ClienteService.create(ClienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@RequestBody @Valid ClienteDTO dadosCliente, @PathVariable Integer id) {
        Cliente Cliente = ClienteService.update(id, dadosCliente);
        return ResponseEntity.ok().body(new ClienteDTO(Cliente));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ClienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

}

