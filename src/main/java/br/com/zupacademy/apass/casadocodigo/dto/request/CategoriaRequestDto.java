package br.com.zupacademy.apass.casadocodigo.dto.request;

import br.com.zupacademy.apass.casadocodigo.modelo.Categoria;
import br.com.zupacademy.apass.casadocodigo.validation.constraints.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * Modelo de transferia para receber os dados de uma requisição para adicionar uma nova Categoria.
 */
public class CategoriaRequestDto {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    /**
     * Construtor com os dados obrigatórios.
     * @param nome
     */

    public CategoriaRequestDto(@JsonProperty("nome") @NotBlank String nome) {
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
     * Converte esse objeto de transferência para o objeto de domínio Categoria.
     * @return
     */
    public Categoria converte() {
        return new Categoria(this.nome);
    }
}
