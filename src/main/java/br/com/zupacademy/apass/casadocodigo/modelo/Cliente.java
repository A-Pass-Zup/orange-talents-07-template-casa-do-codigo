package br.com.zupacademy.apass.casadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String sobrenome;

    @NotBlank
    @Column(nullable = false)
    private String documento;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String endereco;

    @NotBlank
    @Column(nullable = false)
    private String complemento;

    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pais pais;

    @ManyToOne
    private Estado estado;

    @NotBlank
    @Column(nullable = false)
    private String telefone;

    @NotBlank
    @Column(nullable = false)
    private String cep;

    @Deprecated
    private Cliente(){
    }

    public Cliente(
            @NotEmpty String email,
            @NotEmpty String nome,
            @NotEmpty String sobrenome,
            @NotEmpty String documento,
            @NotEmpty String endereco,
            @NotEmpty String complemento,
            @NotEmpty String cidade,
            @NotNull Pais pais,
            Estado estado,
            @NotEmpty String telefone,
            @NotEmpty String cep) {

        Assert.hasLength(email, "Não pode cliente sem nome!");
        Assert.hasLength(nome, "Não pode criar cliente sem nome!");
        Assert.hasLength(sobrenome, "Não pode criar cliente sem sobrenome!");
        Assert.hasLength(documento, "Não pode criar cliente sem documento!");
        Assert.hasLength(endereco, "Não pode criar cliente sem endereço!");
        Assert.hasLength(complemento, "Não pode criar cliente sem complemento!");
        Assert.notNull(pais, "Não pode criar cliente sem país!");
        Assert.hasLength(telefone, "Não pode criar cliente sem telefone!");
        Assert.hasLength(cep, "Não pode criar cliente sem cep!");

        if(estado != null) {
            Assert.isTrue(pais.equals(estado.getPais()), "O país do cliente e do estado precisam ser iguais!");
        }

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;


        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }
}
