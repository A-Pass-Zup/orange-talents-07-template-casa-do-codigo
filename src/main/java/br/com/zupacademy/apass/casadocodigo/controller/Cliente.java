package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.ClienteRequestDto;
import br.com.zupacademy.apass.casadocodigo.validation.ClientePaisEstadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cliente")
public class Cliente {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClientePaisEstadoValidator clientePaisEstadoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(this.clientePaisEstadoValidator);
    }

    @PostMapping
    @Transactional
    public Long cadastra(@RequestBody @Valid ClienteRequestDto clienteRequestDto) {
        var cliente = clienteRequestDto.converte(this.entityManager);

        this.entityManager.persist(cliente);

        return cliente.getId();
    }
}
