package br.com.zupacademy.apass.casadocodigo.modelo;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Deprecated
    private Pais() {
    }

    public Pais(@NotBlank String nome) {
        Assert.hasLength(nome, "Não pode criar um país sem nome!");

        this.nome = nome.trim();

    }
}
