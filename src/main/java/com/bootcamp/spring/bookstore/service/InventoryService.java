package com.bootcamp.spring.bookstore.service;

import com.bootcamp.spring.bookstore.entity.Inventory;

public interface InventoryService {

    Inventory updateAvailableQuantity(String id, int newQuantity) throws Exception;

    Inventory update(Inventory inventory);
}
