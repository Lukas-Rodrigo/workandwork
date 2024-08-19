package com.lucasteixeira.workandwork.domain;

import com.lucasteixeira.workandwork.domain.dtos.TecnicoDTO;
import com.lucasteixeira.workandwork.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa{

    @OneToMany(mappedBy = "tecnico")
    List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }


    public Tecnico(TecnicoDTO tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.perfils = tecnico.getPerfils().stream().map(x -> x.getCode()).collect(Collectors.toSet());

    }

    public List<Chamado> getChamados() {
        return chamados;
    }

}
