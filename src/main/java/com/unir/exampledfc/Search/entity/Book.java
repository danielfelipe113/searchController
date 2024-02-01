package com.unir.exampledfc.search.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unir.exampledfc.search.dto.BookCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int publishedYear;
    private String isbn10;
    private String isbn13;
    private String image;
    @Lob
    private String synopsis;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Critic> critics;

    private String genre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Author author;

    public Book(BookCreateDTO bookCreateDTO) {
        this.name = bookCreateDTO.getName();
        this.publishedYear = bookCreateDTO.getPublishedYear();
        this.isbn10 = bookCreateDTO.getIsbn10();
        this.isbn13 = bookCreateDTO.getIsbn13();
        this.image = bookCreateDTO.getImage();
        this.synopsis = bookCreateDTO.getSynopsis();
        this.genre = bookCreateDTO.getGenre();
    }

    @JsonProperty("authorId")
    public Long getAuthorId() {
        return author != null ? author.getId() : null;
    }
}
