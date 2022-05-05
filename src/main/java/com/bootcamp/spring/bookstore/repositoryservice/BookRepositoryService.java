package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Book;

import java.util.List;

public interface BookRepositoryService {

    Book insert(Book book);

    List<Book> findAll();

    void deleteById(String id);

    Book updateById(Book book);

    Book findById(String id);

    List<Book> findByNames(List<String> names);

    List<Book> findByAuthorId(String authorId);
}
