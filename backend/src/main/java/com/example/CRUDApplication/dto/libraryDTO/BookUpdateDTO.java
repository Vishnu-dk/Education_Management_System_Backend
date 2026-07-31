package com.example.CRUDApplication.dto.libraryDTO;

import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import jakarta.validation.constraints.*;

public class BookUpdateDTO {

    @Size(max = 30)
    private String isbn;

    @Size(min = 2, max = 255)
    private String title;

    @Size(min = 2, max = 255)
    private String author;

    @Size(min = 2, max = 255)
    private String publisher;

    @Min(1800)
    @Max(2100)
    private Integer publicationYear;

    private BookCategory category;

    @Min(0)
    private Integer totalCopies;

    @Min(0)
    private Integer availableCopies;

    private Boolean active;

    public String getIsbn() {
        return isbn;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}