package com.example.CRUDApplication.controller.libraryController;

import com.example.CRUDApplication.dto.libraryDTO.BookAdminResponseDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookCreateDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookUpdateDTO;
import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import com.example.CRUDApplication.service.libraryService.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/books")
public class BookAdminController {

    private final BookService bookService;

    public BookAdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookCreateDTO dto) {

        bookService.createBook(dto);

        return ResponseEntity.ok(
                "Book Created Successfully"
        );
    }

    @GetMapping
    public ResponseEntity<List<BookAdminResponseDTO>> getAllBooks(
            @RequestParam(required = false)
            String title,
            @RequestParam(required = false)
            BookCategory category,
            @RequestParam(required = false)
            String sortBy,
            @RequestParam(required = false)
            String sortDirection,
            @RequestParam(required = false)
            Boolean active
    ) {

        return ResponseEntity.ok(
                bookService.getAllBooksForAdmin(
                        title,category,sortBy,sortDirection,active
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookAdminResponseDTO> getBookById(@PathVariable UUID id) {

        return ResponseEntity.ok(
                bookService.getBookById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook( @PathVariable UUID id, @Valid @RequestBody BookUpdateDTO dto) {

        bookService.updateBook(id, dto);

        return ResponseEntity.ok(
                "Book Updated Successfully"
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateBook(@PathVariable UUID id) {

        bookService.deactivateBook(id);

        return ResponseEntity.ok(
                "Book Deactivated Successfully"
        );
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateBook(@PathVariable UUID id) {

        bookService.activateBook(id);

        return ResponseEntity.ok(
                "Book Activated Successfully"
        );
    }
}
