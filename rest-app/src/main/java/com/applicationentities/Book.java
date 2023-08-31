package com.applicationentities;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String book_id;
    private String book_title;
    private String book_author;
    private float book_price;

    public Book() {
        super();
    }

    public Book(String book_id, String book_title, String book_author, float book_price) {
        super();
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_price = book_price;
    }

    public String getId() {
        return book_id;
    }

    public void setId(String id) {
        this.book_id = id;
    }

    public String getBookTitle() {
        return book_title;
    }

    public void setBookTitle(String title) {
        this.book_title = title;
    }

    public String getAuthorName() {
        return book_author;
    }

    public void setAuthorName(String name) {
        this.book_author = name;
    }

    public float getBookPrice() {
        return book_price;
    }

    public void setBookPrice(float price) {
        this.book_price = price;
    }
}
