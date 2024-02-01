package com.unir.exampledfc.search.service;


import com.unir.exampledfc.search.entity.Author;
import com.unir.exampledfc.search.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public ResponseEntity<Author> findById(Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Long id, Author authorUpdate) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorUpdate.getName());
                    return authorRepository.save(author);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
