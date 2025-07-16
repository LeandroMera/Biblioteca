package org.gorditodev.minibiblioteca.services;

import org.gorditodev.minibiblioteca.entities.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorService {

    Autor saveAutor(Autor autor);
    Optional<Autor> findAutor(Long id);
    Optional<Autor> findAutorByName(String name);
    List<Autor> findAllAutor();
    Autor updateAutor(Autor autor);
    void deleteAutor(Long id) throws ClassNotFoundException;

    List<Autor> findAutorByIds(List<Long> ids);
}


