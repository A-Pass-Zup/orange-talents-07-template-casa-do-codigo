package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.PaisRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/pais")
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PaisRequestDto paisRequestDto) {
        this.entityManager.persist(paisRequestDto.converte());
        return ResponseEntity.ok().build();
    }
}
