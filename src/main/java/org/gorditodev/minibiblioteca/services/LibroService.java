package org.gorditodev.minibiblioteca.services;

import org.gorditodev.minibiblioteca.entities.Autor;
import org.gorditodev.minibiblioteca.entities.Categoria;
import org.gorditodev.minibiblioteca.entities.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    Libro saveLibro(Libro libro);

    Optional<Libro> findById(Long id);

    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findAll();
    Libro updateLibro(Libro libro);
    void deleteLibro(Long id);

    List<Libro> findAllByCategoria(Categoria categoria);
}
