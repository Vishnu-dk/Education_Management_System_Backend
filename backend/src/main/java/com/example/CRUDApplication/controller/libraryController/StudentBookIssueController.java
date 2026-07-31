package com.example.CRUDApplication.controller.libraryController;

import com.example.CRUDApplication.dto.libraryDTO.BookIssueResponseDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookRequestDTO;
import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import com.example.CRUDApplication.service.libraryService.BookIssueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/student/book-requests")
public class StudentBookIssueController {

    private final BookIssueService bookIssueService;

    public StudentBookIssueController(
            BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @PostMapping
    public ResponseEntity<String> requestBook(
            @Valid @RequestBody BookRequestDTO dto){

        bookIssueService.requestBook(dto);

        return ResponseEntity.ok(
                "Book Request Submitted"
        );
    }

    @GetMapping("/my-books")
    public ResponseEntity<List<BookIssueResponseDTO>>
    getMyBooks(
            @RequestParam(required = false)
            Boolean active,
            @RequestParam(required = false)
            LocalDate approveDate,
            @RequestParam(required = false)
            LocalDate dueDate,
            @RequestParam(required = false)
            IssueStatus status,
            @RequestParam(required = false)
            String sortDirection,
            @RequestParam(required = false)
            String sortBy
    ){

        return ResponseEntity.ok(
                bookIssueService.getStudentBooks(active,approveDate,dueDate,status,sortDirection,sortBy)
        );
    }
}
