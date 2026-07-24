package com.example.CRUDApplication.controller;

import com.example.CRUDApplication.dto.StudentLoginDTO;
import com.example.CRUDApplication.dto.UserRegisterDTO;
import com.example.CRUDApplication.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> authLogin(@Valid @RequestBody StudentLoginDTO studentLoginDTO){

        String token=authenticationService.loginStudent(studentLoginDTO);
        return ResponseEntity.ok("Bearer Token : "+token) ;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid
            @RequestBody UserRegisterDTO dto){

        authenticationService.registerUser(dto);

        return ResponseEntity.ok(
                "User Registered Successfully"
        );
    }
}
