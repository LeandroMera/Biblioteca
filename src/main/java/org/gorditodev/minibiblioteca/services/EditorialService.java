package org.gorditodev.minibiblioteca.services;

import org.gorditodev.minibiblioteca.entities.Editorial;

import java.util.List;
import java.util.Optional;

public interface EditorialService {

    Editorial saveEditorial(Editorial editorial);
    Optional<Editorial> findById(Long id);
    Optional<Editorial> findByNombre(String nombre);
    List<Editorial> findAll();
    Editorial updateEditorial(Editorial editorial);
    void deleteEditorial(Long id);
}
