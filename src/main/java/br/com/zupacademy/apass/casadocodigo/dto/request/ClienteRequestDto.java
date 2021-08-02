package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Cliente;
import br.com.zupacademy.apass.casadocodigo.modelo.Estado;
import br.com.zupacademy.apass.casadocodigo.modelo.Pais;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.CpfOrCnpj;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.ExistsId;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClienteRequestDto {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CpfOrCnpj
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldId = "id")
    private Long paisId;

    private Long estadoId;

    @NotBlank
    private String telefone;

    @NotBlank
    @Pattern(regexp = "(^\\d{5}-\\d{3}$)")
    private String cep;

    public ClienteRequestDto(
            @NotBlank String email,
            @NotBlank String nome,
            @NotBlank String sobrenome,
            @NotBlank String documento,
            @NotBlank String endereco,
            @NotBlank String complemento,
            @NotBlank String cidade,
            @NotNull Long paisId,
            Long estadoId,
            @NotBlank String telefone,
            @NotBlank String cep) {

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente converte(EntityManager entityManager) {
        Estado estado = null;

        if(this.estadoId != null)
            estado = entityManager.find(Estado.class, estadoId);

        return new Cliente(
                this.email,
                this.nome,
                this.sobrenome,
                this.documento,
                this.endereco,
                this.complemento,
                this.cidade,
                entityManager.find(Pais.class, this.paisId),
                estado,
                this.telefone,
                this.cep
        );
    }

    public Long getPaisId() {
        return this.paisId;
    }

    public Long getEstadoId() {
        return this.estadoId;
    }
}
