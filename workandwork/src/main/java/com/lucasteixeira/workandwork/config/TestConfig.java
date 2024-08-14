package com.lucasteixeira.workandwork.config;

import com.lucasteixeira.workandwork.domain.Chamado;
import com.lucasteixeira.workandwork.domain.Cliente;
import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.enums.Perfil;
import com.lucasteixeira.workandwork.enums.Prioridade;
import com.lucasteixeira.workandwork.enums.Status;
import com.lucasteixeira.workandwork.repositories.ChamadoRepository;
import com.lucasteixeira.workandwork.repositories.ClienteaRepository;
import com.lucasteixeira.workandwork.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteaRepository clienteaRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Override
    public void run(String... args) throws Exception {


        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "32339532086", "valdir@gmail.com", "123");
        tec1.addPerfil(Perfil.TECNICO);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "35246360636", "linus@gmail.com", "123");
        cli1.addPerfil(Perfil.CLIENTE);

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", cli1, tec1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));

        clienteaRepository.saveAll(Arrays.asList(cli1));

        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
