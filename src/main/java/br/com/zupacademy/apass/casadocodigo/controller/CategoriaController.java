package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.CategoriaRequestDto;
import br.com.zupacademy.apass.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/categoria")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid CategoriaRequestDto categoriaRequestDto) {
        this.categoriaRepository.save(categoriaRequestDto.converte());
        return ResponseEntity.ok().build();
    }
}
