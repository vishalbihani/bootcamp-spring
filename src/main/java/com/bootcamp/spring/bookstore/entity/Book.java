package com.bootcamp.spring.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * An Entity which will contain the information
 * about the book, and the author id
 */
@Table("BOOK")
public class Book implements Persistable<String> {

    /*
     * Spring data needs an identifier to tell which field will
     * act as ID for the row. @Id tells Spring data
     * that this field is the primary key and will be unique.
     */
    @Id
    @Column("ID")
    private String id;

    /*
     * Variable names and DB column names can be different at times
     * so in order to tell the Spring Data to map which variable to
     * which column we use @Column which takes in the
     * DB column name as the value.
     */
    @Column("NAME")
    private String name;

    @Column("AUTHOR_ID")
    private String authorId;

    /*
     * Some fields need to be stored in the DB, to identify such
     * fields we use @Transient annotation.
     */
    @Transient
    private boolean isNew;

    public Book(String id, String name, String authorId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;

        isNew = true;
    }

    public String getId() {
        return id;
    }

    /*
    Spring Data uses this method to identify whether this
    already exist in the DB or not. CrudRepository provides
    save() method which performs the save and insert operation.

    If this method returns false and no such entry exist in the DB
    then spring data will throw an exception that this id is not found
     */
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
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
}
