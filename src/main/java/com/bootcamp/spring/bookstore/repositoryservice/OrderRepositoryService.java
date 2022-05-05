package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Order;

import java.util.List;

public interface OrderRepositoryService {

    List<Order> saveAll(List<Order> orders);

    Order save(Order order);

    List<Order> findAllByUserId(String userId);
}
