package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.dto.OrderRequest;
import com.bootcamp.spring.bookstore.dto.OrderResponse;
import com.bootcamp.spring.bookstore.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponse processOrder(OrderRequest orderRequest);

    List<Order> findAllByUserId(String userId);
}
