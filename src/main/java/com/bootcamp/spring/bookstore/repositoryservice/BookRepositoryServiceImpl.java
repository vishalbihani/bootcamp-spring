package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRepositoryServiceImpl implements BookRepositoryService {

    @Autowired
    private BookRepository repository;

    @Override
    public Book insert(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Book updateById(Book book) {
        return insert(book);
    }

    @Override
    public Book findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Book> findByNames(List<String> names) {
        return repository.getBookByNames(names);
    }

    @Override
    public List<Book> findByAuthorId(String authorId) {
        return repository.findByAuthorId(authorId);
    }
}
