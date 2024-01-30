package com.unir.exampledfc.Search.service;

import com.unir.exampledfc.Search.entity.Author;
import com.unir.exampledfc.Search.entity.Book;
import com.unir.exampledfc.Search.entity.Critic;
import com.unir.exampledfc.Search.repository.AuthorRepository;
import com.unir.exampledfc.Search.repository.BookRepository;
import com.unir.exampledfc.Search.repository.CriticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeeder {
    private final BookRepository bookRepository;
    private final CriticRepository criticRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public DatabaseSeeder(BookRepository bookRepository, CriticRepository criticRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.criticRepository = criticRepository;
        this.authorRepository = authorRepository;
    }

    public void seedData() {
        // Create and save sample data
        Author author1 = new Author();
        author1.setName("Author 1");

        Author author2 = new Author();
        author2.setName("Author 2");

        Author authorSaved1 = authorRepository.save(author1);
        Author authorSaved2 = authorRepository.save(author2);

        Book book1 = new Book();
        book1.setName("Book 1");

        book1.setPublishedYear(2001);
        book1.setIsbn10("1234567890");
        book1.setIsbn13("1234567890123");
        book1.setImage("https://picsum.photos/400/200");
        book1.setSynopsis("A classic novel set in the Roaring Twenties...");
        book1.setGenre("Action");
        book1.setAuthor(authorSaved1);


        Book book2 = new Book();
        book2.setName("Book 2");

        book2.setPublishedYear(2002);
        book2.setIsbn10("1234567892");
        book2.setIsbn13("1234567890122");
        book2.setImage("https://picsum.photos/400/200");
        book2.setSynopsis("2 A classic novel set in the Roaring Twenties...");
        book2.setGenre("Action 2");
        book2.setAuthor(authorSaved2);

        Book savedBook1 = bookRepository.save(book1);
        Book savedBook2 = bookRepository.save(book2);

        Critic critic1 = new Critic();
        critic1.setTitle("Critic 1");
        critic1.setDescription("Critic 1 description");
        critic1.setBook(savedBook1);

        Critic critic2 = new Critic();
        critic2.setTitle("Critic 2");
        critic2.setDescription("Critic 2 description");
        critic2.setBook(savedBook2);

        criticRepository.save(critic1);
        criticRepository.save(critic2);

    }
}
