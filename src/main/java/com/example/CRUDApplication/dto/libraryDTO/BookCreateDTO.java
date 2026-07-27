package com.example.CRUDApplication.dto.libraryDTO;

import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import jakarta.validation.constraints.*;

public class BookCreateDTO {



    @NotBlank
    @Size(min = 2, max = 255)
    private String title;

    @NotBlank
    @Size(min = 2, max = 255)
    private String author;

    @NotBlank
    @Size(min = 2, max = 255)
    private String publisher;

    @Min(1800)
    @Max(2100)
    private Integer publicationYear;

    @NotNull
    private BookCategory category;

    @NotNull
    @Min(1)
    private Integer totalCopies;




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
}