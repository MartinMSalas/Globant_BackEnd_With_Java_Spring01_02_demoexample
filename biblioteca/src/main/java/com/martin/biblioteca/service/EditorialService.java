package com.martin.biblioteca.service;

import com.martin.biblioteca.entity.Editorial;
import com.martin.biblioteca.repository.EditorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    private final EditorialRepository editorialRepository;
    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }
    @Transactional(readOnly = true)
    public Optional<Editorial> getEditorialById(String editorialId) {
        Optional<Editorial> editorialOptional = editorialRepository.findById(editorialId);
        return editorialOptional;
    }
    @Transactional
    public Editorial createEditorial(Editorial editorial) {
        Editorial editorialSaved = editorialRepository.save(editorial);
        if(editorialSaved == null) {
            throw new RuntimeException("Editorial not saved");
        }
        return editorialSaved;
    }
    @Transactional
    public Editorial createEditorial(String name) {
        Editorial editorial = new Editorial();
        editorial.setName(name);
        Editorial editorialSaved = editorialRepository.save(editorial);
        if(editorialSaved == null) {
            throw new RuntimeException("Editorial not saved");
        }
        return editorialSaved;
    }
    @Transactional(readOnly = true)
    public List<Editorial> getAllEditorials() {
        List<Editorial> editorialList = editorialRepository.findAll();
        return editorialList;
    }
}
