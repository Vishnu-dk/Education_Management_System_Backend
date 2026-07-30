package com.example.CRUDApplication.controller.libraryController;

import com.example.CRUDApplication.dto.libraryDTO.BookStudentResponseDTO;
import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import com.example.CRUDApplication.service.libraryService.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student/books")
public class BookStudentController {

    private final BookService bookService;

    public BookStudentController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get all active books
     */
    @GetMapping
    public ResponseEntity<List<BookStudentResponseDTO>> getAllBooks(
            @RequestParam(required = false)
            String title,
            @RequestParam(required = false)
            BookCategory category,
            @RequestParam(required = false)
            String sortBy,
            @RequestParam(required = false)
            String sortDirection
    ) {

        List<BookStudentResponseDTO> books =
                bookService.getAllBooksForStudents(title,category,sortBy,sortDirection);

        return ResponseEntity.ok(books);
    }

    /**
     * Get a specific book
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookStudentResponseDTO> getBookById(
            @PathVariable UUID id) {

        BookStudentResponseDTO book =
                bookService.getBookForStudent(id);

        return ResponseEntity.ok(book);
    }
}