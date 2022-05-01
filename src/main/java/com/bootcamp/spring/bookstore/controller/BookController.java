package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final String INSERT_SUCCESSFUL = "Insert successful";

    /*
     * @Autowired annotation is used to inject beans
     * in to the variable.
     */
    @Autowired
    private BookService bookService;

    @PostMapping("/book/insert")
    public ResponseEntity<ResponseBody> insertBook(@RequestBody Book book) {
        /*
        By default, the isNew flag in the book entity is set to true,
        so no need to set it explicitly.
         */
        bookService.insert(book);

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
}
