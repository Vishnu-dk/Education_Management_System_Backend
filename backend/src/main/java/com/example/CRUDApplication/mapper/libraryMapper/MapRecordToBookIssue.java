package com.example.CRUDApplication.mapper.libraryMapper;

import com.example.CRUDApplication.entity.libraryEntity.BookIssue;
import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import org.jooq.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MapRecordToBookIssue {

    public static BookIssue recordToBookIssueMap(
            Record record){

        if(record == null){
            return null;
        }

        BookIssue bookIssue = new BookIssue();

        bookIssue.setId(record.get("issue_id", UUID.class));
        bookIssue.setStudentId(record.get("student_id", UUID.class));
        bookIssue.setBookId(record.get("book_id", UUID.class));
        bookIssue.setLibrarianId(record.get("librarian_id", UUID.class));

        bookIssue.setRequestDate(record.get("request_date",LocalDate.class));
        bookIssue.setApprovedDate(record.get("approved_date",LocalDate.class));
        bookIssue.setDueDate(record.get( "due_date",LocalDate.class));

        bookIssue.setReturnDate(record.get("return_date",LocalDate.class));
        bookIssue.setFineAmount(record.get("fine_amount",BigDecimal.class));
        Boolean finePaid =record.get("fine_paid",Boolean.class);
        bookIssue.setFinePaid(finePaid != null && finePaid);
        String status =record.get("status",String.class);
        Boolean active =record.get("active", Boolean.class);
        bookIssue.setActive(active != null && active);

        if(status != null){
            bookIssue.setStatus(
                    IssueStatus.valueOf(status)
            );
        }

        return bookIssue;
    }
}