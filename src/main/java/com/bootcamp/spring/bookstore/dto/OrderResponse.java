package com.bootcamp.spring.bookstore.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private String userId;

    private List<ItemDetails> successful;

    private List<ItemDetails> failed;

    public OrderResponse() {
    }

    public OrderResponse(String userId) {
        this.userId = userId;
        successful = new ArrayList<>();
        failed = new ArrayList<>();
    }

    public OrderResponse(String userId, List<ItemDetails> successful, List<ItemDetails> failed) {
        this.userId = userId;
        this.successful = successful;
        this.failed = failed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ItemDetails> getSuccessful() {
        return successful;
    }

    public void setSuccessful(List<ItemDetails> successful) {
        this.successful = successful;
    }

    public List<ItemDetails> getFailed() {
        return failed;
    }

    public void setFailed(List<ItemDetails> failed) {
        this.failed = failed;
    }

    public void addItemToSuccessList(ItemDetails item) {
        successful.add(item);
    }

    public void addItemToFailedList(ItemDetails item) {
        failed.add(item);
    }
}
