package com.bootcamp.spring.bookstore.repository;

import com.bootcamp.spring.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findAllByUserId(@Param("userId") String userId);
}
