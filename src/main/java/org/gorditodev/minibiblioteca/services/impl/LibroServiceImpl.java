package org.gorditodev.minibiblioteca.services.impl;

import org.gorditodev.minibiblioteca.entities.Categoria;
import org.gorditodev.minibiblioteca.entities.Libro;
import org.gorditodev.minibiblioteca.repositories.LibroRepository;
import org.gorditodev.minibiblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Libro saveLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Optional<Libro> findByTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro updateLibro(Libro libro) {
        return libroRepository.save(libro);// se guarda igualmente el id se encarga el codigo
    }

    @Override
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);

    }

    @Override
    public List<Libro> findAllByCategoria(Categoria categoria) {
        return libroRepository.findByCategoria(categoria);
    }
}
