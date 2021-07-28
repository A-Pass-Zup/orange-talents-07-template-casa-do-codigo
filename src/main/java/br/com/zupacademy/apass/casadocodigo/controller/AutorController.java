package br.com.zupacademy.apass.casadocodigo.controller;

import br.com.zupacademy.apass.casadocodigo.dto.request.AutorRequestDto;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.apass.casadocodigo.validator.AutorEmailUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private AutorEmailUnicoValidator autorEmailUnicoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(autorEmailUnicoValidator);
    }

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
