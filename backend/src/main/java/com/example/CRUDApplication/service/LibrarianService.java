package com.example.CRUDApplication.service;

import com.example.CRUDApplication.dto.adminDTO.LibrarianResponseDTO;
import com.example.CRUDApplication.dto.adminDTO.LibrarianUpdateDTO;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.mapper.adminMapper.MapRecordToLibrarianResponseDTO;
import com.example.CRUDApplication.repository.adminRepository.LibrarianRepository;
import org.jooq.Record;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public LibrarianService(
            LibrarianRepository librarianRepository) {

        this.librarianRepository = librarianRepository;
    }

    public LibrarianResponseDTO getById(UUID id){

        Record record =
                librarianRepository.findById(id);

        return MapRecordToLibrarianResponseDTO.mapRecordToLibrarianResponseDTO(record);

    }

    public void updateById(UUID id,LibrarianUpdateDTO dto){

        boolean updated =
                librarianRepository
                        .updateLibrarian(id, dto);

        if(!updated){
            throw new ResourceNotFoundException(
                    "Librarian not found"
            );
        }
    }

    public void deleteById(UUID id) {
        boolean deleted=librarianRepository.deleteLibrarian(id);
        if(!deleted){
            throw new RuntimeException(
                    "Deleted Failed"
            );
        }
    }
}