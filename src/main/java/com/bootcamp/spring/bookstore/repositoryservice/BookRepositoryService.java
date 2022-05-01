package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Book;

import java.util.List;

public interface BookRepositoryService {

    Book insert(Book book);

    List<Book> findAll();

    void deleteById(String id);
}
