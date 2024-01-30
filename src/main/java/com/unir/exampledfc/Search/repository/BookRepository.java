package com.unir.exampledfc.Search.repository;

import com.unir.exampledfc.Search.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

}