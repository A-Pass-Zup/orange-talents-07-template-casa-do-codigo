package br.com.zupacademy.apass.casadocodigo.repository;

import br.com.zupacademy.apass.casadocodigo.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNome(String nome);
}
