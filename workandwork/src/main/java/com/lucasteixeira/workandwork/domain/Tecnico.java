package com.lucasteixeira.workandwork.domain;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa{

    List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }
}
