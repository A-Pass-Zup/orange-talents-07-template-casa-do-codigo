package br.com.zupacademy.apass.cadadocodigo.repository;


import br.com.zupacademy.apass.cadadocodigo.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
