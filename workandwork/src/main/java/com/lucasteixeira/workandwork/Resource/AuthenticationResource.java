package com.lucasteixeira.workandwork.Resource;

import com.lucasteixeira.workandwork.domain.Pessoa;
import com.lucasteixeira.workandwork.domain.dtos.AuthenticationDTO;
import com.lucasteixeira.workandwork.domain.dtos.LoginResponseDTO;
import com.lucasteixeira.workandwork.repositories.PessoaRepository;
import com.lucasteixeira.workandwork.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {


    @Autowired
    TokenService tokenService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dados) {
        Pessoa pessoa = this.pessoaRepository.findByEmail(dados.email()).orElseThrow(() -> new RuntimeException("Email not Found"));
        if (passwordEncoder.matches(dados.senha(), pessoa.getSenha())){
            String token = this.tokenService.generateToken(pessoa);
            return ResponseEntity.ok().body(new LoginResponseDTO(token));
        }
        return ResponseEntity.badRequest().build();
    }
}
