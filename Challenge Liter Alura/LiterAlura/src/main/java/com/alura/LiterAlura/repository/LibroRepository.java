package com.alura.LiterAlura.repository;

import com.alura.LiterAlura.model.Lenguaje;
import com.alura.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idiomas")
    List<Libro> findByLanguage(@Param("idiomas") Lenguaje idiomas);

    @Query("SELECT l FROM Libro l WHERE LOWER(l.autor.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Libro> findByName(@Param("nombre") String nombre);

    List<Libro> findByTituloContainingIgnoreCase(String title);
}
