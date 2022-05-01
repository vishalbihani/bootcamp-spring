package com.bootcamp.spring.bookstore.repository;

import com.bootcamp.spring.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
@Repository annotation is used to create repositories for each table.
Here BookRepository is an interface which is extending the CrudRepository.
CrudRepository takes in the Entity and the datatype of the primary key / id.

CrudRepository provides multiple methods to perform multiple operations
on the Table like save, update, find, list, delete etc. which can be used
without implementation.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
