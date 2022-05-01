package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.repositoryservice.BookRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Service is a spring stereotype which means this class
 * contains business-logic or provides service functionalities.
 *
 * Spring will auto-scan this classes and will create beans for
 * the same. These beans are managed by the Spring IoC and can be
 * injected in other classes.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepositoryService repositoryService;

    @Override
    public Book insert(Book book) {
        return repositoryService.insert(book);
    }
}
