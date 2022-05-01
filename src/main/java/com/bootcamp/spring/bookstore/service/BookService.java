package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Book;

import java.util.List;

/**
 * Whenever there is inter-service operations, it
 * is better to use Interfaces.
 */
public interface BookService {

    Book insert(Book book);

    List<Book> findAll();

    void deleteById(String id);

    Book updateById(String id, Book book);
}
