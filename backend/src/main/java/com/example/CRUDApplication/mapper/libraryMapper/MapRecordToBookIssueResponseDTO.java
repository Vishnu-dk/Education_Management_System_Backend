package com.example.CRUDApplication.mapper.libraryMapper;

import com.example.CRUDApplication.dto.libraryDTO.BookIssueResponseDTO;
import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import org.jooq.Record;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MapRecordToBookIssueResponseDTO {

    public static BookIssueResponseDTO map(Record record){

        if(record == null){
            return null;
        }

        BookIssueResponseDTO dto =
                new BookIssueResponseDTO();

        dto.setIssueId(record.get("issue_id", UUID.class));

        dto.setStudentName(record.get("student_name", String.class));
        dto.setBookTitle(record.get("book_title", String.class));
        dto.setLibrarianName(record.get("librarian_name", String.class));
        dto.setRequestDate(record.get("request_date", LocalDate.class));
        dto.setApprovedDate(record.get("approved_date", LocalDate.class));
        dto.setDueDate(record.get("due_date", LocalDate.class));
        dto.setReturnDate(record.get("return_date", LocalDate.class));
        dto.setFineAmount(record.get("fine_amount", BigDecimal.class));
        Boolean finePaid =record.get("fine_paid", Boolean.class);
        dto.setFinePaid(finePaid != null && finePaid);
        String status =record.get("status", String.class);

        if(status != null){
            dto.setStatus(
                    IssueStatus.valueOf(status)
            );
        }

        return dto;
    }
}