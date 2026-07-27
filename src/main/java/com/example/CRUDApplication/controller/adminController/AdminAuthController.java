package com.example.CRUDApplication.controller.adminController;

import com.example.CRUDApplication.dto.adminDTO.AdminLoginDTO;
import com.example.CRUDApplication.dto.adminDTO.AdminRegisterDTO;
import com.example.CRUDApplication.service.AdminAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(
            AdminAuthService adminAuthService) {

        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(
            @Valid @RequestBody AdminRegisterDTO dto){

        adminAuthService.registerAdmin(dto);

        return ResponseEntity.ok(
                "Admin Registered Successfully"
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(
            @Valid @RequestBody AdminLoginDTO dto){

        String token =
                adminAuthService.loginAdmin(dto);

        return ResponseEntity.ok(
                "Bearer Token : " + token
        );
    }
}