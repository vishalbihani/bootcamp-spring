package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Book;

/**
 * Whenever there is inter-service operations, it
 * is better to use Interfaces.
 */
public interface BookService {

    Book insert(Book book);
}
