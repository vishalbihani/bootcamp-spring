package com.bootcamp.spring.bookstore.dto;

public class BookResource {

    private String id;

    private String name;

    private String authorId;

    private int maxAllowedQuantity;

    private int availableQuantity;

    public BookResource() {}

    public BookResource(String id, String name, String authorId, int maxAllowedQuantity, int availableQuantity) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.maxAllowedQuantity = maxAllowedQuantity;
        this.availableQuantity = availableQuantity;
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
}
