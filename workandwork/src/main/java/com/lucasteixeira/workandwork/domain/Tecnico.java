package com.lucasteixeira.workandwork.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasteixeira.workandwork.domain.dtos.TecnicoDTO;
import com.lucasteixeira.workandwork.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa{

    @JsonIgnore
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Perfil> perfilSet = perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
        return perfilSet.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
