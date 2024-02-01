package com.unir.exampledfc.search.repository;

import com.unir.exampledfc.search.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}