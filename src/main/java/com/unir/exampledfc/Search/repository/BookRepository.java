package com.unir.exampledfc.search.repository;

import com.unir.exampledfc.search.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

}