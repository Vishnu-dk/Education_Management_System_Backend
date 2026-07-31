package com.example.CRUDApplication.service;

import com.example.CRUDApplication.dto.adminDTO.AdminLoginDTO;
import com.example.CRUDApplication.dto.adminDTO.AdminRegisterDTO;
import com.example.CRUDApplication.entity.adminEntity.AdminRole;
import com.example.CRUDApplication.exception.DuplicateUserException;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.repository.adminRepository.AdminAuthRepository;
import com.example.CRUDApplication.repository.adminRepository.LibrarianRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public  class AdminAuthService {

    private final AdminAuthRepository adminAuthRepository;
    private final LibrarianRepository librarianRepository;
    private final JWTService jwtService;

    public AdminAuthService(
            AdminAuthRepository adminAuthRepository,
            LibrarianRepository librarianRepository,
            JWTService jwtService) {

        this.adminAuthRepository = adminAuthRepository;
        this.librarianRepository = librarianRepository;
        this.jwtService = jwtService;
    }

    public void registerAdmin(AdminRegisterDTO dto){

        if(adminAuthRepository.emailExists(dto.getEmail())){
            throw new DuplicateUserException(
                    "Email already exists"
            );
        }

        UUID adminId =
                adminAuthRepository.registerAdmin(dto);

        if(dto.getRole() == AdminRole.LIBRARIAN){
            librarianRepository.createEmptyLibrarian(
                    adminId
            );
        }
    }

    public String loginAdmin(AdminLoginDTO dto){

        boolean isLogin =
                adminAuthRepository.loginAdmin(dto);

        if(!isLogin){
            throw new ResourceNotFoundException(
                    "Invalid Credentials"
            );
        }

        return jwtService.generateToken(dto.getEmail(),"ADMIN");
    }
}