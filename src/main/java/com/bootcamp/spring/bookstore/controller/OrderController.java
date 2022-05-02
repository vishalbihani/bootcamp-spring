package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.dto.OrderItemDetails;
import com.bootcamp.spring.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private BookService bookService;

    @PostMapping("/order")
    public ResponseEntity<ResponseBody> placeOrder(@RequestBody List<String> names) {
        List<OrderItemDetails> orderItemDetails = bookService.order(names);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.CREATED.value(), orderItemDetails),
                HttpStatus.CREATED
        );
    }
}
