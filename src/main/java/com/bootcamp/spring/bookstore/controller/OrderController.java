package com.bootcamp.spring.bookstore.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.bookstore.dto.OrderRequest;
import com.bootcamp.spring.bookstore.dto.OrderResponse;
import com.bootcamp.spring.bookstore.entity.Order;
import com.bootcamp.spring.bookstore.service.BookService;
import com.bootcamp.spring.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ResponseBody> placeOrder(@RequestBody OrderRequest request) {

        OrderResponse response;
        try {
            response = orderService.processOrder(request);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Something went wrong! Order cannot be fulfilled"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.CREATED.value(), response),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/order/all")
    public ResponseEntity<ResponseBody> findAllByUserId(@RequestParam(name = "userId") String userId) {
        List<Order> orders = orderService.findAllByUserId(userId);

        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), orders),
                HttpStatus.OK
        );
    }
}
