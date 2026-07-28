package com.example.CRUDApplication.dto.libraryDTO;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BookRequestDTO {

    @NotNull
    private UUID bookId;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
