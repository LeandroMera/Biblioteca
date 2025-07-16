package org.gorditodev.minibiblioteca.services;

import org.gorditodev.minibiblioteca.entities.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    Categoria savecategoria(Categoria categoria);
    Categoria findById(Long id);
    Categoria findByNombre(String nombre);
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
    List<Categoria> findAll();

    Categoria updateCategoria(Categoria categoria);
    void deleteCategoria(Long id);

}
