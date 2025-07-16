package org.gorditodev.minibiblioteca.services.impl;

import org.gorditodev.minibiblioteca.entities.Autor;
import org.gorditodev.minibiblioteca.entities.Libro;
import org.gorditodev.minibiblioteca.repositories.AutorRepository;
import org.gorditodev.minibiblioteca.repositories.LibroRepository;
import org.gorditodev.minibiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor saveAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Optional<Autor> findAutor(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public Optional<Autor> findAutorByName(String name) {
        return autorRepository.findByNombre(name);
    }

    @Override
    public List<Autor> findAllAutor() {
        return autorRepository.findAll();
    }

    @Override
    public Autor updateAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void deleteAutor(Long id) throws ClassNotFoundException {
        Optional<Autor> optionalAutor = autorRepository.findById(id);
        if (optionalAutor.isPresent()) {
            Autor autor = optionalAutor.get();
            eliminarRelacionesDelAutor(autor);
            autorRepository.deleteById(id);
        }else {
            throw  new ClassNotFoundException("Error");
        }//evita errores futuros todas las lineas anteriores d eeliminar
    }

    @Override
    public List<Autor> findAutorByIds(List<Long> ids) {
        return autorRepository.findAllById(ids);
    }


    private void eliminarRelacionesDelAutor(Autor autor) {
        for (Libro libro : autor.getLibros()) {
            libro.getAutores().remove(autor);//remueve la relacion entre autor, autores y libro
        }
        autor.getLibros().clear();
    }
}
