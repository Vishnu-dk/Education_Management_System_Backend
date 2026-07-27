package com.example.CRUDApplication.mapper.libraryMapper;

import com.example.CRUDApplication.dto.libraryDTO.BookAdminResponseDTO;
import com.example.CRUDApplication.entity.libraryEntity.Book;

public class MapBookToAdminResponseDTO {

    public static BookAdminResponseDTO bookToAdminResponseDTO(Book book){

        if(book == null){
            return null;
        }

        BookAdminResponseDTO dto =
                new BookAdminResponseDTO();

        dto.setId(book.getId());

        dto.setTitle(book.getTitle());

        dto.setAuthor(book.getAuthor());

        dto.setPublisher(book.getPublisher());

        dto.setPublicationYear(
                book.getPublicationYear()
        );

        dto.setCategory(book.getCategory());

        dto.setTotalCopies(book.getTotalCopies());

        dto.setAvailableCopies(book.getAvailableCopies());

        return dto;
    }
}