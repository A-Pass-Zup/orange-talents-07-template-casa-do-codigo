package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Pais;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.UniqueValue;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class PaisRequestDto {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaisRequestDto(String nome) {
        this.nome = nome.trim();
    }

    public Pais converte() {
        return new Pais(this.nome);
    }
}
