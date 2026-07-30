package com.example.CRUDApplication.controller.libraryController;

import com.example.CRUDApplication.dto.libraryDTO.BookIssueResponseDTO;
import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import com.example.CRUDApplication.service.libraryService.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AdminBookIssueController {


    @RestController
    @RequestMapping("/api/admin/book-requests")
    public static class adminBookIssueController {

        private final BookIssueService bookIssueService;

        public adminBookIssueController(
                BookIssueService bookIssueService) {
            this.bookIssueService = bookIssueService;
        }

        @GetMapping
        public ResponseEntity<List<BookIssueResponseDTO>>getAllRequests(
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
                    bookIssueService.getAllRequests(active,approveDate,dueDate,status,sortDirection,sortBy)
            );
        }

        @PatchMapping("/{issueId}/approve")
        public ResponseEntity<String> approveRequest(@PathVariable UUID issueId){

            bookIssueService.approveRequest(issueId);

            return ResponseEntity.ok("Request Approved");
        }

        @PatchMapping("/{issueId}/reject")
        public ResponseEntity<String> rejectRequest(@PathVariable UUID issueId){

            bookIssueService.rejectRequest(issueId);

            return ResponseEntity.ok("Request Rejected");
        }

        @PatchMapping("/{issueId}/return")
        public ResponseEntity<String> returnBook(@PathVariable UUID issueId){

            bookIssueService.returnBook(issueId);

            return ResponseEntity.ok("Book Returned");
        }

        @PatchMapping("/{issueId}/fine-paid")
        public ResponseEntity<String> markFinePaid(@PathVariable UUID issueId){

            bookIssueService.markFinePaid(issueId);

            return ResponseEntity.ok("Fine Marked Paid");
        }
    }
}
