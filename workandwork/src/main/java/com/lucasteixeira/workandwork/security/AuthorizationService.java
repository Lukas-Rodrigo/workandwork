package com.lucasteixeira.workandwork.security;

import com.lucasteixeira.workandwork.domain.Pessoa;
import com.lucasteixeira.workandwork.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + username));

        return new User(
                pessoa.getEmail(),
                pessoa.getSenha(),
                pessoa.getPerfils().stream()
                        .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                        .collect(Collectors.toSet())
        );
    }
}
