package com.unir.exampledfc.Search.service;

import com.unir.exampledfc.Search.entity.Book;
import com.unir.exampledfc.Search.entity.Critic;
import com.unir.exampledfc.Search.repository.BookRepository;
import com.unir.exampledfc.Search.repository.CriticRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriticService {
    private final CriticRepository criticRepository;
    private final BookRepository bookRepository;

    public CriticService(CriticRepository criticRepository, BookRepository bookRepository) {
        this.criticRepository = criticRepository;
        this.bookRepository = bookRepository;
    }

    public List<Critic> findAll() {
        return criticRepository.findAll();
    }

    public ResponseEntity<Critic> findById(Long id) {
        return criticRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Critic save(Critic critic) {
        Book book = bookRepository.findById(critic.getBookId()).orElseThrow(
                () -> new OpenApiResourceNotFoundException("Book not found with id: " + critic.getBookId())
        );
        critic.setBook(book);
        return criticRepository.save(critic);
    }

    public Critic update(Long id, Critic criticUpdate) {
        return criticRepository.findById(id)
                .map(critic -> {
                    critic.setTitle(criticUpdate.getTitle());
                    critic.setDescription(criticUpdate.getDescription());
                    Book book = bookRepository.findById(criticUpdate.getBookId()).orElseThrow(
                            () -> new OpenApiResourceNotFoundException("Book not found with id: " + critic.getBookId())
                    );
                    critic.setBook(book);
                    return criticRepository.save(critic);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        criticRepository.deleteById(id);
    }
}
