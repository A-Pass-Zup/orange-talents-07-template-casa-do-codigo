package br.com.zupacademy.apass.cadadocodigo.dto.request;

import br.com.zupacademy.apass.cadadocodigo.modelo.Autor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Classe DTO que representa os dados de requisição para cadastro de Autor.
 */
public class AutorRequestDto {

    @NotEmpty
    private String nome;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Length(max = 400)
    private String descricao;

    /**
     * Construtor com os atributos obrigatórios.
     *
     * @param nome
     * @param email
     * @param descricao
     */
    public AutorRequestDto(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
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
