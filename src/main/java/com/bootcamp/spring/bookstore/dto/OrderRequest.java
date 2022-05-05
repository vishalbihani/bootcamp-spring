package com.bootcamp.spring.bookstore.dto;

import java.util.List;

public class OrderRequest {

    private String userId;

    private List<ItemDetails> orderList;

    public OrderRequest() {
    }

    public OrderRequest(String userId, List<ItemDetails> orderList) {
        this.userId = userId;
        this.orderList = orderList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ItemDetails> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<ItemDetails> orderList) {
        this.orderList = orderList;
    }
}
