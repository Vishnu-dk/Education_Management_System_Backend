package com.example.CRUDApplication.service.libraryService;

import com.example.CRUDApplication.dto.libraryDTO.BookAdminResponseDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookCreateDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookStudentResponseDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookUpdateDTO;
import com.example.CRUDApplication.entity.libraryEntity.Book;
import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import com.example.CRUDApplication.exception.DuplicateUserException;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.mapper.libraryMapper.MapBookToAdminResponseDTO;
import com.example.CRUDApplication.mapper.libraryMapper.MapBookToStudentResponseDTO;
import com.example.CRUDApplication.mapper.libraryMapper.MapRecordToBook;
import com.example.CRUDApplication.repository.libraryRepository.BookRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(BookCreateDTO dto){

        boolean exists =
                bookRepository.existsBook(
                        dto.getTitle(),
                        dto.getAuthor()
                );

        if(exists){
            throw new DuplicateUserException(
                    "Book already exists"
            );
        }

        bookRepository.createBook(dto);
    }

    public BookAdminResponseDTO getBookById(UUID id){

        Record record =
                bookRepository.findById(id);

        Book book = Optional.ofNullable(record)
                .map(MapRecordToBook::recordToBookMap)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found"
                        ));

        return MapBookToAdminResponseDTO
                .bookToAdminResponseDTO(book);
    }
    public BookStudentResponseDTO getBookForStudent(UUID id){

        Record record =
                bookRepository.findById(id);

        Book book = Optional.ofNullable(record)
                .map(MapRecordToBook::recordToBookMap)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found"
                        ));

        return MapBookToStudentResponseDTO
                .bookToStudentResponseDTO(book);
    }

    public List<BookAdminResponseDTO> getAllBooksForAdmin(String title, BookCategory category,String sortBy,String sortDirection,Boolean active){

        List<Record> records =
                bookRepository.findAllForAdmin(title,category,sortBy,sortDirection,active);

        if(records == null || records.isEmpty()){
            throw new ResourceNotFoundException(
                    "No books found"
            );
        }

        return records.stream()
                .map(MapRecordToBook::recordToBookMap)
                .map(MapBookToAdminResponseDTO::bookToAdminResponseDTO)
                .toList();
    }

    public List<BookStudentResponseDTO> getAllBooksForStudents(String title, BookCategory category, String sortBy, String sortDirection){

        List<Record> records =
                bookRepository.findAll(title,category,sortBy,sortDirection);

        if(records == null || records.isEmpty()){
            throw new ResourceNotFoundException(
                    "No books found"
            );
        }

        return records.stream()
                .map(MapRecordToBook::recordToBookMap)
                .map(MapBookToStudentResponseDTO::bookToStudentResponseDTO)
                .toList();
    }

    public void updateBook(
            UUID id,
            BookUpdateDTO dto){

        Record existingBook =
                bookRepository.findById(id);

        if(existingBook == null){
            throw new ResourceNotFoundException(
                    "Book not found"
            );
        }

        if(dto.getTotalCopies() != null
                && dto.getAvailableCopies() != null
                && dto.getAvailableCopies()
                > dto.getTotalCopies()){

            throw new RuntimeException(
                    "Available copies cannot exceed total copies"
            );
        }

        boolean updated =
                bookRepository.updateBook(id, dto);

        if(!updated){
            throw new RuntimeException(
                    "Book update failed"
            );
        }
    }

    public void deactivateBook(UUID id){

        boolean deactivated =
                bookRepository.deactivateBook(id);

        if(!deactivated){
            throw new ResourceNotFoundException(
                    "Book not found"
            );
        }
    }

    public void activateBook(UUID id){

        boolean activated =
                bookRepository.activateBook(id);

        if(!activated){
            throw new ResourceNotFoundException(
                    "Book not found"
            );
        }
    }
}
