package br.com.zupacademy.apass.cadadocodigo.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Entidade de domínio que representa o autor.
 */
@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(max = 400)
    private String descricao;

    @NotNull
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    /**
     * Contrutor com dados obrigatórios para autor.
     * @param nome
     * @param email
     * @param descricao
     */
    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        if( nome==null || nome.trim().equals("")) {
            throw new IllegalArgumentException("Não pode criar autor sem nome!");
        }

        if(email==null || email.trim().equals("")) {
            throw new IllegalArgumentException("Não pode criar autor sem e-mail!");
        }

        if(descricao==null || descricao.trim().equals("") || descricao.trim().length() >= 400) {
            throw new IllegalArgumentException("A descrição do autor não pode está vazia!");
        }

        if(descricao.length() > 400) {
            throw new IllegalArgumentException("A descrição do autor só pode ter no máximo 400 caracteres!");
        }

        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    /**
     * Obtém o id do autor.
     * @return
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Obtém o nome do autor.
     * @return
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém o e-mail do autor.
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Obtém a descrição do autor.
     * @return
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Obtém a data/hora de criação do autor.
     * @return
     */
    public LocalDateTime getDataHoraCriacao() {
        return this.dataHoraCriacao;
    }
}
