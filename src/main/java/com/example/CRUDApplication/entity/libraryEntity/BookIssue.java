package com.example.CRUDApplication.entity.libraryEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "BOOK_ISSUE")
public class BookIssue {

    @Id
    @Column(name = "issue_id")
    private UUID id;

    @Column(name = "student_id",nullable = false)
    private UUID studentId;

    @Column(name = "book_id",nullable = false)
    private UUID bookId;

    @Column(name = "librarian_id")
    private UUID librarianId;

    @Column(name = "request_date",nullable = false)
    private LocalDate requestDate;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "fine_amount")
    private BigDecimal fineAmount;

    @Column(name = "fine_paid")
    private boolean finePaid=false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IssueStatus status;


    @Column(name = "active")
    private boolean active;

    public BookIssue() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(UUID librarianId) {
        this.librarianId = librarianId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isFinePaid() {
        return finePaid;
    }

    public void setFinePaid(boolean finePaid) {
        this.finePaid = finePaid;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}