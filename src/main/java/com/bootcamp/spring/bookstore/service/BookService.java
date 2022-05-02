package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.entity.Book;

import java.util.List;

/**
 * Whenever there is inter-service operations, it
 * is better to use Interfaces.
 */
public interface BookService {

    Book insert(BookResource bookResource);

    List<Book> findAll();

    void deleteById(String id);

    Book updateById(Book book);

    Book findById(String id);

    List<Book> findByAuthorId(String authorId);

    void order(List<String> bookNames);
}
