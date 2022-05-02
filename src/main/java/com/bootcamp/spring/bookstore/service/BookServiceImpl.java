package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.dto.OrderItemDetails;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repositoryservice.BookRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return repositoryService.updateById(book);
    }

    @Override
    public Book findById(String id) {
        return repositoryService.findById(id);
    }

    @Override
    public List<Book> findByAuthorId(String authorId) {
        List<Book> books = findAll();
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if (authorId.equals(book.getAuthorId())) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<OrderItemDetails> order(List<String> bookNames) {
        List<Book> books = repositoryService.findByNames(bookNames);
        List<OrderItemDetails> details = new ArrayList<>();

        for (Book book : books) {
            Inventory inventory = book.getInventory();
            int availableQuantity = inventory.getAvailableQuantity();

            if (availableQuantity < 1) {
                details.add(new OrderItemDetails(
                        book.getName(), 0, "No available quantity"
                ));
                continue;
            }

            --availableQuantity;
            inventory.setAvailableQuantity(availableQuantity);

            inventoryService.update(inventory);
            details.add(new OrderItemDetails(
                    book.getName(), 1, "Order placed"
            ));
        }

        return details;
    }
}
