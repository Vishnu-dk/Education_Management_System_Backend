package com.example.CRUDApplication.controller.adminController;

import com.example.CRUDApplication.dto.adminDTO.LibrarianResponseDTO;
import com.example.CRUDApplication.dto.adminDTO.LibrarianUpdateDTO;
import com.example.CRUDApplication.service.LibrarianService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    public LibrarianController(
            LibrarianService librarianService) {

        this.librarianService = librarianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrarianResponseDTO>
    getLibrarian(@PathVariable UUID id){

        return ResponseEntity.ok(
                librarianService.getById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>
    updateLibrarian(
            @PathVariable UUID id,
            @Valid
            @RequestBody LibrarianUpdateDTO dto){

        librarianService.updateById(id, dto);

        return ResponseEntity.ok(
                "Librarian Updated Successfully"
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteLibrarian(
            @PathVariable UUID id){
        librarianService.deleteById(id);
        return ResponseEntity.ok(
                "Librarian Data Deleted Successfully"
        );
    }
}