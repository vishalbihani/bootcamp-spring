package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repositoryservice.BookRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
public class BookServiceImplTest {
    private static Book book;
    private static BookResource bookResource;

    @Mock
    private BookRepositoryService repositoryService;

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();


    @BeforeAll
    public static void init() {
        book = new Book("1", "System design", "A123");
        Inventory inventory = new Inventory(25, 20, book);
        book.setInventory(inventory);
        bookResource = new BookResource("1", "System design", "A123", 25, 20);
    }

    @Test
    public void insertShouldReturnBook() {
        when(repositoryService.insert(any())).thenReturn(book);

        Book insertedBook = bookService.insert(bookResource);

        Assertions.assertEquals(book.getId(), insertedBook.getId());
        Assertions.assertEquals(book.getName(), insertedBook.getName());
        Assertions.assertEquals(book.getAuthorId(), insertedBook.getAuthorId());
        Assertions.assertEquals(
                book.getInventory().getAvailableQuantity(), insertedBook.getInventory().getAvailableQuantity()
        );
        Assertions.assertEquals(
                book.getInventory().getMaxAllowedQuantity(), insertedBook.getInventory().getMaxAllowedQuantity()
        );
    }

    @Test
    public void testFindByAuthorId() {
        Book book1 = new Book("1", "Book1", "1");
        Book book2 = new Book("2", "Book2", "2");
        Book book3 = new Book("3", "Book3", "1");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        when(repositoryService.findAll()).thenReturn(books);

        List<Book> filteredBooks = bookService.findByAuthorId("1");

        Assertions.assertEquals(2, filteredBooks.size());
    }

    @Test
    public void testFindByAuthorIdNoBooksFound() {
        List<Book> books = new ArrayList<>();
        when(repositoryService.findAll()).thenReturn(books);

        List<Book> filteredBooks = bookService.findByAuthorId("authorId");

        Assertions.assertEquals(0, filteredBooks.size());
    }
}
