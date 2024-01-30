package com.unir.exampledfc.Search.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    private String name;
    private int publishedYear;
    private String isbn10;
    private String isbn13;
    private Long authorId;
    private String image;
    private String synopsis;
    private String genre;
}
