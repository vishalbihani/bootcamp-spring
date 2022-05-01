package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRepositoryServiceImpl implements BookRepositoryService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book insert(Book book) {
        return repository.save(book);
    }
}
