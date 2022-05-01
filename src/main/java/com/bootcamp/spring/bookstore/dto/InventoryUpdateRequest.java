package com.bootcamp.spring.bookstore.dto;

public class InventoryUpdateRequest {
    private int availableQuantity;

    public InventoryUpdateRequest() {}

    public InventoryUpdateRequest(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
