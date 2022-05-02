package com.bootcamp.spring.bookstore.dto;

public class OrderItemDetails {

    private String bookName;

    private int quantity;

    private String message;

    public OrderItemDetails(String bookName, int quantity, String message) {
        this.bookName = bookName;
        this.quantity = quantity;
        this.message = message;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
