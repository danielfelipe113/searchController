package com.unir.exampledfc.Search.service;


import com.unir.exampledfc.Search.dto.BookCreateDTO;
import com.unir.exampledfc.Search.entity.Author;
import com.unir.exampledfc.Search.entity.Book;
import com.unir.exampledfc.Search.entity.BookSpecification;
import com.unir.exampledfc.Search.repository.AuthorRepository;
import com.unir.exampledfc.Search.repository.BookRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findById(Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Book save(BookCreateDTO book) {

        Optional<Author> author = authorRepository.findById(book.getAuthorId());

        if(author.isEmpty()) {
            throw new OpenApiResourceNotFoundException("Author not found with id: " + book.getAuthorId());
        }
        Book bookToSave = new Book(book);

        bookToSave.setAuthor(author.get());

        return bookRepository.save(bookToSave);
    }

    public Book update(Long id, Book bookUpdate) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(bookUpdate.getName());
                    book.setPublishedYear(bookUpdate.getPublishedYear());
                    book.setIsbn10(bookUpdate.getIsbn10());
                    book.setIsbn13(bookUpdate.getIsbn13());
                    book.setImage(bookUpdate.getImage());
                    book.setSynopsis(bookUpdate.getSynopsis());
                    book.setGenre(bookUpdate.getGenre());
                    return bookRepository.save(book);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(Map<String, Object> searchCriteria) {
        Specification<Book> spec = Specification.where(null);

        for (Map.Entry<String, Object> entry : searchCriteria.entrySet()) {
            spec = spec.and(BookSpecification.hasAttribute(entry.getKey(), entry.getValue()));
        }

        return bookRepository.findAll(spec);
    }
}
