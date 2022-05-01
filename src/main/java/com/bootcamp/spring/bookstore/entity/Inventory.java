package com.bootcamp.spring.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    private String id;

    @Column(name = "MAX_QTY", nullable = false)
    private int maxAllowedQuantity;

    @Column(name = "AVL_QTY", nullable = false)
    private int availableQuantity;

    /*
    One-to-One mapping with shared primary key
     */
    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Book book;

    public Inventory() {}

    public Inventory(int maxAllowedQuantity, int availableQuantity, Book book) {
        this.maxAllowedQuantity = maxAllowedQuantity;
        this.availableQuantity = availableQuantity;
        this.book = book;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxAllowedQuantity() {
        return maxAllowedQuantity;
    }

    public void setMaxAllowedQuantity(int maxAllowedQuantity) {
        this.maxAllowedQuantity = maxAllowedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    /*
    To remove cyclic conversion. We can also use @JsonManagedReference with
    @JsonBackReference or use @JsonIdentityInfo.
     */
    @JsonIgnore
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
