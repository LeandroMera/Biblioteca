package org.gorditodev.minibiblioteca.services.impl;

import org.gorditodev.minibiblioteca.entities.Editorial;
import org.gorditodev.minibiblioteca.repositories.EditorialRepository;
import org.gorditodev.minibiblioteca.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public Editorial saveEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Optional<Editorial> findById(Long id) {
        return editorialRepository.findById(id);
    }

    @Override
    public Optional<Editorial> findByNombre(String nombre) {
        return editorialRepository.findByNombre(nombre);
    }

    @Override
    public List<Editorial> findAll() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial updateEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public void deleteEditorial(Long id) {
        editorialRepository.deleteById(id);
    }
}
