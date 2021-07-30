package br.com.zupacademy.apass.casadocodigo.repository;

import br.com.zupacademy.apass.casadocodigo.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
