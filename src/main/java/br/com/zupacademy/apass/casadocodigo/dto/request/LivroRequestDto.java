package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Autor;
import br.com.zupacademy.apass.casadocodigo.modelo.Categoria;
import br.com.zupacademy.apass.casadocodigo.modelo.Livro;
import br.com.zupacademy.apass.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.apass.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.ExistsId;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.IsFutureLocalDate;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequestDto {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroDePaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @IsFutureLocalDate
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldId = "id")
    private Long autor;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldId = "id")
    private Long categoria;

    public LivroRequestDto(
            @NotBlank String titulo,
            @NotBlank @Size(max = 500) String resumo,
            @NotBlank String sumario,
            @NotNull @Min(20) BigDecimal preco,
            @NotNull @Min(100) Integer numeroDePaginas,
            @NotBlank String isbn,
            @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataPublicacao,
            @NotNull Long autor,
            @NotNull Long categoria) {
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

    public Livro converte(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numeroDePaginas,
                this.isbn,
                this.dataPublicacao,
                autorRepository.getById(this.autor),
                categoriaRepository.getById(this.categoria));
    }
}
