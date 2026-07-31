package com.example.CRUDApplication.mapper.adminMapper;

import com.example.CRUDApplication.dto.adminDTO.LibrarianResponseDTO;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import org.jooq.Record;

public class MapRecordToLibrarianResponseDTO {

    public static LibrarianResponseDTO mapRecordToLibrarianResponseDTO(Record record){
        if(record == null){
            throw new ResourceNotFoundException(
                    "Librarian not found"
            );
        }

        LibrarianResponseDTO dto =
                new LibrarianResponseDTO();

        dto.setName(record.get("librarian_name",String.class));
        dto.setPhoneNo(record.get("phone_no",String.class));
        dto.setDateOfJoin(record.get("date_of_join",java.time.LocalDate.class));

        return dto;

    }
}
