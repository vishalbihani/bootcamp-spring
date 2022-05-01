package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Inventory;

public interface InventoryRepositoryService {

    Inventory findById(String id);

    Inventory update(Inventory inventory);
}
