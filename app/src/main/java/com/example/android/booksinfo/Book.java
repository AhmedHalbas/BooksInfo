package com.example.android.booksinfo;

public class Book {

   private String title,author,publisher,date,description,imageURL;

    public Book(String title, String author, String publisher, String date, String description, String imageURL) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.date = date;
        this.description = description;
        this.imageURL = imageURL;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
