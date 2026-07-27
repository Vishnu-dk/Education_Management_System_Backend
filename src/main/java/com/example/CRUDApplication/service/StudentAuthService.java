package com.example.CRUDApplication.service;

import com.example.CRUDApplication.dto.studentDTO.StudentLoginDTO;
import com.example.CRUDApplication.dto.studentDTO.StudentRegisterDTO;
import com.example.CRUDApplication.exception.DuplicateUserException;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.repository.studentRepository.StudentAuthRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentAuthService {

    private final StudentAuthRepository studentAuthRepository;
    private final JWTService jwtService;

    public StudentAuthService(
            StudentAuthRepository studentAuthRepository, JWTService jwtService) {
        this.studentAuthRepository = studentAuthRepository;

        this.jwtService = jwtService;
    }

    public void registerStudent(StudentRegisterDTO dto){

        if(studentAuthRepository.emailExists(dto.getEmail())){
            throw new DuplicateUserException(
                    "Email already exists"
            );
        }

        studentAuthRepository.registerStudent(dto);
    }

    public String loginStudent(StudentLoginDTO dto){

        boolean isLogin =
                studentAuthRepository.loginStudent(dto);

        if(!isLogin){
            throw new ResourceNotFoundException(
                    "Invalid Credentials"
            );
        }

        return jwtService.generateToken(dto.getEmail(),"STUDENT");
    }
}