package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Order;
import com.bootcamp.spring.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRepositoryServiceImpl implements OrderRepositoryService {

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> saveAll(List<Order> orders) {
        return repository.saveAll(orders);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> findAllByUserId(String userId) {
        return repository.findAllByUserId(userId);
    }
}
