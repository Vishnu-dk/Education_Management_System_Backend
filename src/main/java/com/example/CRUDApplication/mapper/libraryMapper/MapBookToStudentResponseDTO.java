package com.example.CRUDApplication.mapper.libraryMapper;

import com.example.CRUDApplication.dto.libraryDTO.BookStudentResponseDTO;
import com.example.CRUDApplication.entity.libraryEntity.Book;

public class MapBookToStudentResponseDTO {

    public static BookStudentResponseDTO
    bookToStudentResponseDTO(Book book){

        if(book == null){
            return null;
        }

        BookStudentResponseDTO dto =
                new BookStudentResponseDTO();

        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory());
        dto.setAvailableCopies(book.getAvailableCopies());

        return dto;
    }
}