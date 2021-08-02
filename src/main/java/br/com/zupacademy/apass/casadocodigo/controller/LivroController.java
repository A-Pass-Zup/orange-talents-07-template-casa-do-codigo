package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.LivroRequestDto;
import br.com.zupacademy.apass.casadocodigo.dto.response.LivroItemListaResponsoDto;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.apass.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.apass.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public ResponseEntity<?> lista(@PageableDefault Pageable page) {
        return ResponseEntity.ok(this.livroRepository
                .findAll(page)
                .map(LivroItemListaResponsoDto::new));
    }

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid LivroRequestDto livroRequestDto) {
        this.livroRepository.save(livroRequestDto
                .converte(this.autorRepository, this.categoriaRepository));

        return ResponseEntity.ok().build();
    }
}
