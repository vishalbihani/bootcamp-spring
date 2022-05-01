package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Inventory;
import com.bootcamp.spring.bookstore.repositoryservice.InventoryRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepositoryService repositoryService;

    @Override
    public Inventory updateAvailableQuantity(String id, int availableQuantity) throws Exception {
        Inventory inventory = repositoryService.findById(id);
        int maxAllowedQuantity = inventory.getMaxAllowedQuantity();

        if (maxAllowedQuantity >= availableQuantity) {
            inventory.setAvailableQuantity(availableQuantity);
            return repositoryService.update(inventory);

        } else {
            log.warn("Available quantity cannot be more than max allowed quantity");
            throw new Exception("Available quantity cannot be more than max allowed quantity");
        }
    }
}
