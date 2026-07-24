package com.example.CRUDApplication.service;

import com.example.CRUDApplication.dto.StudentLoginDTO;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.repository.AuthenticationRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {


    private final JWTService jwtService;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(JWTService jwtService, AuthenticationRepository authenticationRepository) {
        this.jwtService = jwtService;
        this.authenticationRepository = authenticationRepository;
    }

    public String loginStudent(StudentLoginDTO studentLoginDTO){
        boolean isLogin=authenticationRepository.studentLogin(studentLoginDTO);
        if(!isLogin){
            throw new ResourceNotFoundException("Invalid Credentials");
        }
        return jwtService.generateToken(studentLoginDTO);
    }
}
