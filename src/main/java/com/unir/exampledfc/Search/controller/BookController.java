package com.unir.exampledfc.Search.controller;

import com.unir.exampledfc.Search.dto.BookCreateDTO;
import com.unir.exampledfc.Search.entity.Book;
import com.unir.exampledfc.Search.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam Map<String, Object> searchCriteria) {
        List<Book> books = bookService.searchBooks(searchCriteria);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookCreateDTO book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

