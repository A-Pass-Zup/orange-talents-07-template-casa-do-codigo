package br.com.zupacademy.apass.casadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"pais_id", "nome"}))
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pais pais;


    /**
     * Construtor necessário para JPA.
     */
    @Deprecated
    private Estado(){
    }

    /**
     * Construtor com os dados iniciais obrigatórios.
     *
     * @param nome Obrigatório.
     * @param pais Obrigatório.
     */
    public Estado(@NotBlank String nome, @NotNull Pais pais) {

        Assert.hasLength(nome, "Não pode criar estado sem nome!");
        Assert.notNull(pais, "Não pode criar estado sem país!");

        this.nome = nome.trim();
        this.pais = pais;
    }
}
