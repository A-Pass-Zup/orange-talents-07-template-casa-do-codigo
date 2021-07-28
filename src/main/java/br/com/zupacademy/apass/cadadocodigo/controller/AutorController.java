package br.com.zupacademy.apass.cadadocodigo.controller;

import br.com.zupacademy.apass.cadadocodigo.dto.request.AutorRequestDto;
import br.com.zupacademy.apass.cadadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller()
@RequestMapping("/api/v1/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Endpoint para cadastrar um novo Autor.
     * @param autorReqDto
     */
    @PostMapping
    @Transactional
    public ResponseEntity cadastra(@RequestBody @Valid AutorRequestDto autorReqDto) {
        var autor = autorReqDto.converte();
        autor.setDataHoraCriacao(LocalDateTime.now());
        this.autorRepository.save(autor);
        return ResponseEntity.ok().build();
    }
}
