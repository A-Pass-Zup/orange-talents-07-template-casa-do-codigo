package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Autor;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Classe DTO que representa os dados de requisição para cadastro de Autor.
 */
public class AutorRequestDto {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    @UniqueValue(domainClass = Autor.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param nome
     * @param email
     * @param descricao
     */
    public AutorRequestDto(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    /**
     * Obtém o e-mail do novo autor do RequestDto.
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Cria uma instância de {@link Autor} a partir dos dados da instância dessa classe.
     *
     * @return
     */
    public Autor converte() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
