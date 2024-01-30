package com.unir.exampledfc.Search.repository;

import com.unir.exampledfc.Search.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}