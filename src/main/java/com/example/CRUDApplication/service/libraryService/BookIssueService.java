package com.example.CRUDApplication.service.libraryService;

import com.example.CRUDApplication.dto.libraryDTO.BookIssueResponseDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookRequestDTO;
import com.example.CRUDApplication.entity.adminEntity.Admin;
import com.example.CRUDApplication.entity.libraryEntity.Book;
import com.example.CRUDApplication.entity.libraryEntity.BookIssue;
import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import com.example.CRUDApplication.entity.studentEntity.Student;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.mapper.libraryMapper.MapRecordToBook;
import com.example.CRUDApplication.mapper.libraryMapper.MapRecordToBookIssue;
import com.example.CRUDApplication.mapper.libraryMapper.MapRecordToBookIssueResponseDTO;
import com.example.CRUDApplication.repository.adminRepository.AdminAuthRepository;
import com.example.CRUDApplication.repository.libraryRepository.BookIssueRepository;
import com.example.CRUDApplication.repository.libraryRepository.BookRepository;
import com.example.CRUDApplication.repository.studentRepository.StudentAuthRepository;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final StudentAuthRepository studentAuthRepository;
    private final AdminAuthRepository adminAuthRepository;

    public BookIssueService(
            BookIssueRepository bookIssueRepository,
            BookRepository bookRepository, StudentAuthRepository studentAuthRepository, AdminAuthRepository adminAuthRepository) {

        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.studentAuthRepository = studentAuthRepository;
        this.adminAuthRepository = adminAuthRepository;
    }


    public void requestBook(BookRequestDTO dto) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Student student =
                studentAuthRepository.findByEmail(email);

        UUID studentId =
                student.getId();


        Record activeBook =
                bookRepository.findActiveBookById(dto.getBookId());

        if(activeBook == null){
            throw new ResourceNotFoundException("Book not found or inactive");
        }

        boolean alreadyRequested =
                bookIssueRepository.studentAlreadyRequested(studentId,dto.getBookId() );

        if(alreadyRequested){
            throw new RuntimeException("Book already requested");
        }

        bookIssueRepository.requestBook(studentId,dto.getBookId());
    }


    public void approveRequest(UUID issueId) {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        Admin admin =
                adminAuthRepository.findByEmail(email);

        UUID librarianId =
                admin.getId();



        BookIssue issue=issueRequestValidation(issueId);

        if(issue.getStatus() != IssueStatus.REQUESTED){
            throw new RuntimeException(
                    "Request already processed"
            );
        }

        Record bookRecord =
                bookRepository.findById(issue.getBookId());

        Book book =
                MapRecordToBook.recordToBookMap(bookRecord);

        if(book.getAvailableCopies() <= 0){
            throw new RuntimeException(
                    "No copies available"
            );
        }

        bookIssueRepository.approveRequest(
                issueId,
                librarianId
        );

        bookRepository.reduceCopies(issue.getBookId());
    }


    public void rejectRequest(UUID issueId){

        BookIssue issue=issueRequestValidation(issueId);

        if(issue.getStatus() != IssueStatus.REQUESTED){
            throw new RuntimeException(
                    "Book already processed"
            );
        }

        boolean updated =
                bookIssueRepository.rejectRequest(issueId);

        if(!updated){
            throw new ResourceNotFoundException("Request not found");
        }
    }


    public void returnBook(UUID issueId){

        BookIssue issue=issueRequestValidation(issueId);

        if(issue.getStatus() != IssueStatus.ISSUED){
            throw new RuntimeException(
                    "Book not issued"
            );
        }

        bookRepository.increaseCopies(issue.getBookId());

        LocalDate today = LocalDate.now();

        if(issue.getDueDate() != null &&
                today.isAfter(issue.getDueDate())) {

            long daysLate =
                    ChronoUnit.DAYS.between(issue.getDueDate(),today);

            BigDecimal fine =
                    BigDecimal.valueOf(daysLate * 5);

            bookIssueRepository.updateFine(issueId,fine);
        }

        bookIssueRepository.returnBook(issueId);
    }


    public void markFinePaid(UUID issueId){

        Record issueRecord =
                bookIssueRepository.findById(issueId);

        if(issueRecord == null){
            throw new ResourceNotFoundException(
                    "Request not found"
            );
        }

        boolean updated =
                bookIssueRepository.markFinePaid(issueId);

        if(!updated){
            throw new ResourceNotFoundException(
                    "Request not found"
            );
        }
    }


    public List<BookIssueResponseDTO> getAllRequests(){

        Result<? extends Record> records = bookIssueRepository.findAllRequests();
        return records.stream()
                .map(MapRecordToBookIssueResponseDTO::map)
                .toList();
    }


    public List<BookIssueResponseDTO> getStudentBooks(){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Student student =
                studentAuthRepository.findByEmail(email);

        UUID studentId =
                student.getId();
        Result<? extends Record> records = bookIssueRepository.getStudentBooks(studentId);
        return records.stream()
                .map(MapRecordToBookIssueResponseDTO::map)
                .toList();
    }

    public BookIssue issueRequestValidation(UUID issueId){

        Record issueRecord =
                bookIssueRepository.findById(issueId);

        if(issueRecord == null){
            throw new ResourceNotFoundException(
                    "Request not found"
            );
        }

        BookIssue issue =
                MapRecordToBookIssue
                        .recordToBookIssueMap(issueRecord);

        if(!issue.isActive()){
            throw new RuntimeException(
                    "Request already closed"
            );
        }

        return issue;
    }
}