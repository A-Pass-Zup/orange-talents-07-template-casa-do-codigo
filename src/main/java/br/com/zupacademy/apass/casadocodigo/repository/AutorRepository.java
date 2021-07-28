package br.com.zupacademy.apass.casadocodigo.repository;


import br.com.zupacademy.apass.casadocodigo.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByEmail(String email);
}
