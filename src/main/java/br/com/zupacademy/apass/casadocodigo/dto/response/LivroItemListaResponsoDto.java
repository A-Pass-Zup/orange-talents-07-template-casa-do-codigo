package br.com.zupacademy.apass.casadocodigo.dto.response;

import br.com.zupacademy.apass.casadocodigo.modelo.Livro;

public class LivroItemListaResponsoDto {
    private Long id;

    private String titulo;

    public LivroItemListaResponsoDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
