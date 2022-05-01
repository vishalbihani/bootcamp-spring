package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final String INSERT_SUCCESSFUL = "Insert successful";
    private final String DELETE_SUCCESSFUL = "Delete successful";


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

    @DeleteMapping("/book/delete")
    public ResponseEntity<ResponseBody> deleteById(@RequestParam(name = "id") String id) {
        bookService.deleteById(id);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), DELETE_SUCCESSFUL),
                HttpStatus.OK
        );
    }


    @PutMapping("/book/update")
    public ResponseEntity<ResponseBody> updateById(@RequestParam(name = "id") String id,
                                                   @RequestBody Book book) {

        /*
        To update the existing record we need to set the isNew to false
        in the book entity.
         */
        book.setNew(false);
        Book updatedBook = bookService.updateById(id, book);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), updatedBook),
                HttpStatus.OK
        );
    }
}
