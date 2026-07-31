package com.example.CRUDApplication.entity.libraryEntity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @Column(name = "book_id")
    private UUID id;


    @Column(name = "book_title",nullable = false)
    private String title;

    @Column(name = "author_name",nullable = false)
    private String author;

    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_category")
    private BookCategory category;

    @Column(name = "total_copies",nullable = false)
    private Integer totalCopies;

    @Column(name = "available_copies",nullable = false)
    private Integer availableCopies;

    @Column(name = "active")
    private boolean active;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
