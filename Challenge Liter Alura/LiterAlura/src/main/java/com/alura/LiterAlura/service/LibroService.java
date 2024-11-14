package com.alura.LiterAlura.service;

import com.alura.LiterAlura.model.Autor;
import com.alura.LiterAlura.model.Libro;
import com.alura.LiterAlura.repository.LibroRepository;
import com.alura.LiterAlura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AuthorRepository autorRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository, AuthorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public String verificarYGuardarLibro(Libro libro) {
        if (libro == null) {
            return "Libro no puede ser nulo.";
        }

        // 1. Verificar si el libro ya existe por su título:
        Optional<Libro> libroExistente = libroRepository.findByTitulo(libro.getTitulo());

        if (libroExistente.isPresent()) {
            return "El libro ya está en la base de datos.";
        }

        // 2. Verificar si el autor ya existe por su nombre:
        try {
            Optional<Autor> autorExistente = autorRepository.findByNombre(libro.getAutor().getNombre());

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                // Si el autor no existe, se guarda uno nuevo
                autor = libro.getAutor();
                autorRepository.save(autor);
            }
            libro.setAutor(autor);

            // 3. Guardar el nuevo libro con el autor ya verificado:
            libroRepository.save(libro);
            return "Libro y autor guardados correctamente.";
        } catch (DataAccessException e) {
            e.printStackTrace();
            return "Error al guardar el libro: " + e.getMessage();
        }
    }

    public List<Libro> searchBooksByTitle(String title) {
        // Verifica si el título es nulo o vacío antes de realizar la búsqueda
        if (title == null || title.trim().isEmpty()) {
            return List.of(); // Devuelve una lista vacía si el título no es válido
        }

        // Realiza la búsqueda utilizando el método del repositorio
        return libroRepository.findByTituloContainingIgnoreCase(title);
    }
}
