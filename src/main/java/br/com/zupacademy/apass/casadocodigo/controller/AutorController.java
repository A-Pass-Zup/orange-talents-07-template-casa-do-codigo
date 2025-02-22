package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.AutorRequestDto;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    /**
     * Endpoint para cadastrar um novo Autor.
     * @param autorReqDto
     */
    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid AutorRequestDto autorReqDto) {
        this.autorRepository.save(autorReqDto.converte());
        return ResponseEntity.ok().build();
    }
}
