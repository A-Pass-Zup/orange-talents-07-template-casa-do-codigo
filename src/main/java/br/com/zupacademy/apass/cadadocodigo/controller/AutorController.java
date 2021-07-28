package br.com.zupacademy.apass.cadadocodigo.controller;

import br.com.zupacademy.apass.cadadocodigo.dto.request.AutorRequestDto;
import br.com.zupacademy.apass.cadadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/autor")
@Validated
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Endpoint para cadastrar um novo Autor.
     * @param autorReqDto
     */
    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid AutorRequestDto autorReqDto) {
        this.autorRepository.save(autorReqDto.converte());
        return ResponseEntity.ok().build();
    }
}
