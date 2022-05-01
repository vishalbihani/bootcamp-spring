package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final String INSERT_SUCCESSFUL = "Insert successful";
    private final String DELETE_SUCCESSFUL = "Delete successful";


    /*
     * @Autowired annotation is used to inject beans
     * in to the variable.
     */
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<ResponseBody> insertBook(@RequestBody BookResource bookResource) {
        bookService.insert(bookResource);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.CREATED.value(), INSERT_SUCCESSFUL),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/book/all")
    public ResponseEntity<ResponseBody> findAll() {
        List<Book> books = bookService.findAll();

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), books),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/book")
    public ResponseEntity<ResponseBody> deleteById(@RequestParam(name = "id") String id) {
        bookService.deleteById(id);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), DELETE_SUCCESSFUL),
                HttpStatus.OK
        );
    }


    @PutMapping("/book")
    public ResponseEntity<ResponseBody> updateById(@RequestBody Book book) {
        Book updatedBook = bookService.updateById(book);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), updatedBook),
                HttpStatus.OK
        );
    }

    @GetMapping("/book")
    public ResponseEntity<ResponseBody> findById(@RequestParam(name = "id") String id) {
        Book book;

        try {
            book = bookService.findById(id);

        } catch (NoSuchElementException e) {
            log.info("Book with id {} not found.", id);
            return new ResponseEntity<>(
                    new ResponseBody(HttpStatus.NOT_FOUND.value(), "Book not found"),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), book),
                HttpStatus.OK
        );
    }

    @GetMapping("/book/author")
    public ResponseEntity<ResponseBody> findByAuthorId(@RequestParam(name = "id") String authorId) {
        List<Book> books = bookService.findByAuthorId(authorId);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), books),
                HttpStatus.OK
        );
    }
}
