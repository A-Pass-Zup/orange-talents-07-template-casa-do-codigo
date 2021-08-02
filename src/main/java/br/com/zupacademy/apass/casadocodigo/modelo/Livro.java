package br.com.zupacademy.apass.casadocodigo.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String titulo;

    @NotBlank
    @NotNull
    @Size(max = 500)
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroDePaginas;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String isbn;

    @NotNull
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    private Autor autor;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    /**
     * Construtor usado pelo JPA.
     * Não utilize esse construtor.
     */
    @Deprecated
    public Livro() {
    }

    /**
     * Construtor com os dados obrigatórios.
     *
     * @param titulo
     * @param resumo
     * @param sumario
     * @param preco
     * @param numeroDePaginas
     * @param isbn
     * @param dataPublicacao
     * @param autor
     * @param categoria
     */
    public Livro(
            @NotBlank String titulo,
            @NotBlank @Size(max = 500) String resumo,
            @NotBlank String sumario,
            @NotNull @Min(20) BigDecimal preco,
            @NotNull @Min(100) Integer numeroDePaginas,
            @NotBlank String isbn,
            @NotNull LocalDate dataPublicacao,
            @NotNull Autor autor,
            @NotNull Categoria categoria) {

        Assert.hasLength(titulo, "Não pode criar livro sem título!");

        Assert.hasLength(resumo, "Não pode criar livro sem resumo");

        Assert.hasLength(sumario, "Não pode criar livro sem sumário!");

        Assert.notNull(preco, "Não pode criar livro sem preço!");
        Assert.isTrue(preco.compareTo(new BigDecimal("20.0")) >= 0, "Preço do livro não pode ser menor do que 20!");

        Assert.notNull(numeroDePaginas, "Não pode criar livro sem o número de páginas!");
        Assert.isTrue(numeroDePaginas >= 100, "O número de páginas do livro precisa ser no mínimo 100!");

        Assert.hasLength(isbn, "Não pode criar livro sem o ISBN!");

        Assert.notNull(dataPublicacao, "Não pode criar o livro sem a data de publicação");
        Assert.isTrue(dataPublicacao.compareTo(LocalDate.now()) > 0, "A data de publicação do livro precisa ser posterior à data atual!");

        Assert.notNull(autor, "Não pode criar livro sem autor!");

        Assert.notNull(categoria, "Não pode criar livro sem categoria!");

        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
        this.categoria = categoria;
    }

    /**
     * Obtém o id do livro.
     * @return
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Obtém o título/nome do livro.
     * @return
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Obtém o resumo do livro.
     * @return
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * Obtém o sumário do livro.
     * @return
     */
    public String getSumario() {
        return sumario;
    }

    /**
     * Obtém o preço do livro.
     * @return
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Obtém o número de páginas do livro.
     * @return
     */
    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    /**
     * Obtém o ISBN do livro.
     * @return
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Obtém o nome da categoria do livro.
     * @return
     */
    public String getNomeCategoria() {
        return this.categoria.getNome();
    }

    /**
     * Obtém o nome do autor do livro.
     * @return
     */
    public String getNomeAutor() {
        return this.autor.getNome();
    }

    /**
     * Obtém a descrição do autor do livro.
     * @return
     */
    public String getDescricaoAutor() {
        return this.autor.getDescricao();
    }
}
