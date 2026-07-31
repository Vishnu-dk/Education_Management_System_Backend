package com.example.CRUDApplication.controller.studentController;

import com.example.CRUDApplication.dto.studentDTO.StudentLoginDTO;
import com.example.CRUDApplication.dto.studentDTO.StudentRegisterDTO;
import com.example.CRUDApplication.service.StudentAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/auth")
public class StudentAuthController {

    private final StudentAuthService studentAuthService;

    public StudentAuthController(StudentAuthService studentAuthService) {
        this.studentAuthService = studentAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(
            @Valid @RequestBody StudentRegisterDTO dto) {

        studentAuthService.registerStudent(dto);

        return ResponseEntity.ok(
                "Student Registered Successfully"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(
            @Valid @RequestBody StudentLoginDTO dto) {

        String token = studentAuthService.loginStudent(dto);

        return ResponseEntity.ok(
                "Bearer Token : " + token
        );
    }
}