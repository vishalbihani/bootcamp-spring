package com.bootcamp.spring.bookstore.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ORDER_DETAILS")
public class Order {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "BOOK_ID")
    private String bookId;

    @Column(name = "QUANTITY")
    private int quantity;

    public Order() {}

    public Order(String id, String orderId, String userId, String bookId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Order(String orderId, String userId, String bookId, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.userId = userId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
