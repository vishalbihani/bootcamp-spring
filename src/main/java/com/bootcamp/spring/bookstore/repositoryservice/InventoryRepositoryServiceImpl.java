package com.bootcamp.spring.bookstore.repositoryservice;

import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryRepositoryServiceImpl implements InventoryRepositoryService {

    @Autowired
    private InventoryRepository repository;

    @Override
    public Inventory findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Inventory update(Inventory inventory) {
        return repository.save(inventory);
    }
}
