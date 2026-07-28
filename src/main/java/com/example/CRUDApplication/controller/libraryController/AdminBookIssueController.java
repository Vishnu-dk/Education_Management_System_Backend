package com.example.CRUDApplication.controller.libraryController;

import com.example.CRUDApplication.dto.libraryDTO.BookIssueResponseDTO;
import com.example.CRUDApplication.service.libraryService.BookIssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity<List<BookIssueResponseDTO>>getAllRequests(){

            return ResponseEntity.ok(
                    bookIssueService.getAllRequests()
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
