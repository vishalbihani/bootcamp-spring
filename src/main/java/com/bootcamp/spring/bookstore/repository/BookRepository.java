package com.bootcamp.spring.bookstore.repository;

import com.bootcamp.spring.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository annotation is used to create repositories for each table.
Here BookRepository is an interface which is extending the JPARepository.
JPARepository extends CrudRepository.
It takes in the Entity and the datatype of the primary key / id.

It provides multiple methods to perform multiple operations
on the Table like save, update, find, list, delete etc. which can be used
without implementation.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT b FROM Book b WHERE b.name IN :names")
    List<Book> getBookByNames(@Param("names") List<String> names);
}
