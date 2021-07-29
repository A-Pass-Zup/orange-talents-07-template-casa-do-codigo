package br.com.zupacademy.apass.casadocodigo.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String nome;

    @Deprecated
    public Categoria() {
    }

    /**
     * Contrutor com os dados obrigatórios.
     * @param nome
     */
    public Categoria(@NotBlank String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o nome da categoria.
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o id da categoria.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o id da categoria.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
