package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.EstadoRequestDto;
import br.com.zupacademy.apass.casadocodigo.validation.EstadoNomeUnicoPorPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/estado")
public class EstadoController {

    @Autowired
    private EstadoNomeUnicoPorPaisValidator estadoNomeUnicoPorPaisValidator;

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(this.estadoNomeUnicoPorPaisValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid EstadoRequestDto estadoRequestDto) {
        this.entityManager.persist(estadoRequestDto.converte(this.entityManager));
        return ResponseEntity.ok().build();
    }
}
