package com.bootcamp.spring.bookstore.entity;

import javax.persistence.*;

/**
 * An Entity which will contain the information
 * about the book, and the author id
 */
@Entity
@Table(name = "BOOK")
public class Book {

    /*
     * Spring data needs an identifier to tell which field will
     * act as ID for the row. @Id tells Spring data
     * that this field is the primary key and will be unique.
     */
    @Id
    @Column(name = "ID")
    private String id;

    /*
     * Variable names and DB column names can be different at times
     * so in order to tell the Spring Data to map which variable to
     * which column we use @Column which takes in the
     * DB column name as the value.
     */
    @Column(name = "NAME")
    private String name;

    @Column(name = "AUTHOR_ID")
    private String authorId;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory inventory;

    public Book() {}

    public Book(String id, String name, String authorId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
