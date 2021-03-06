package com.bootcamp.spring.bookstore.repository;

import com.bootcamp.spring.bookstore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    @Query("SELECT i.availableQuantity FROM Inventory i WHERE i.id = :id")
    int getAvailableQuantityById(@Param("id") String id);
}
