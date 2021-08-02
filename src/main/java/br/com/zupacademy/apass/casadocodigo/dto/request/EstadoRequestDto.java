package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Estado;
import br.com.zupacademy.apass.casadocodigo.modelo.Pais;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.ExistsId;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class EstadoRequestDto {

    @NotBlank
    private String nome;

    @ExistsId(domainClass = Pais.class, fieldId = "id")
    private Long paisId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public EstadoRequestDto(@NotBlank String nome) {
        this.nome = nome.trim();
    }

    public Estado converte(EntityManager entityManager) {
        return new Estado(this.nome, entityManager.find(Pais.class, this.paisId));
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }
}
