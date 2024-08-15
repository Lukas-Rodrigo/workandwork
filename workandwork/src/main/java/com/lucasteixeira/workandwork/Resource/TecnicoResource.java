package com.lucasteixeira.workandwork.Resource;

import com.lucasteixeira.workandwork.domain.Tecnico;
import com.lucasteixeira.workandwork.domain.dtos.TecnicoDTO;
import com.lucasteixeira.workandwork.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico tecnicoobj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnicoobj));
    }
}

