package com.lucasteixeira.workandwork.domain;

import com.lucasteixeira.workandwork.domain.dtos.ClienteDTO;
import com.lucasteixeira.workandwork.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    List<Chamado> chamados = new ArrayList<>();

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO Cliente) {
        this.id = Cliente.getId();
        this.nome = Cliente.getNome();
        this.cpf = Cliente.getCpf();
        this.email = Cliente.getEmail();
        this.senha = Cliente.getSenha();
        this.perfils = Cliente.getPerfils().stream().map(x -> x.getCode()).collect(Collectors.toSet());

    }

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }


    public List<Chamado> getChamados() {
        return chamados;
    }

}
