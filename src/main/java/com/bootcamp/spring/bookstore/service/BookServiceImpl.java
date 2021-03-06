package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repositoryservice.BookRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Book insert(BookResource bookResource) {

        Book book = new Book(
                bookResource.getId(),
                bookResource.getName(),
                bookResource.getAuthorId()
        );
        Inventory inventory = new Inventory(
                bookResource.getMaxAllowedQuantity(),
                bookResource.getAvailableQuantity(),
                book
        );
        book.setInventory(inventory);

        return repositoryService.insert(book);
    }

    @Override
    public List<Book> findAll() {
        return repositoryService.findAll();
    }

    @Override
    public void deleteById(String id) {
        repositoryService.deleteById(id);
    }

    @Override
    public Book updateById(Book book) {
        book.getInventory()
                .setBook(book);
        return repositoryService.updateById(book);
    }

    @Override
    public Book findById(String id) {
        return repositoryService.findById(id);
    }

    @Override
    public List<Book> findByAuthorId(String authorId) {
        return repositoryService.findByAuthorId(authorId);
    }
}
