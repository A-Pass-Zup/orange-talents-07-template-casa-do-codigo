package br.com.zupacademy.apass.casadocodigo.dto.response;

import br.com.zupacademy.apass.casadocodigo.modelo.Livro;

import java.math.BigDecimal;

public class LivroDetalhesResponseDto {
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroDePaginas;
    private String isbn;
    private String categoria;
    private String autor;
    private String descricaoAutor;

    public LivroDetalhesResponseDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.categoria = livro.getNomeCategoria();
        this.autor = livro.getNomeAutor();
        this.descricaoAutor = livro.getDescricaoAutor();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }
}
