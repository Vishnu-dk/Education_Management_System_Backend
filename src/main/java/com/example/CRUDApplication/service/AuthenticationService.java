package com.example.CRUDApplication.service;

import com.example.CRUDApplication.dto.StudentLoginDTO;
import com.example.CRUDApplication.dto.UserRegisterDTO;
import com.example.CRUDApplication.exception.DuplicateUserException;
import com.example.CRUDApplication.exception.ResourceNotFoundException;
import com.example.CRUDApplication.repository.AuthenticationRepository;
import com.example.CRUDApplication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;




@Service
public class AuthenticationService {

    private final JWTService jwtService;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;

    public AuthenticationService(
            JWTService jwtService,
            AuthenticationRepository authenticationRepository,
            UserRepository userRepository) {

        this.jwtService = jwtService;
        this.authenticationRepository =
                authenticationRepository;
        this.userRepository = userRepository;
    }

    public void registerUser(UserRegisterDTO dto) {

        if(userRepository.emailExists(dto.getEmail())) {

            throw new DuplicateUserException(
                    "Email already exists"
            );
        }

        userRepository.createUser(dto);
    }

    public String loginStudent(StudentLoginDTO studentLoginDTO){
        boolean isLogin=authenticationRepository.studentLogin(studentLoginDTO);
        if(!isLogin){
            throw new ResourceNotFoundException("Invalid Credentials");
        }
        return jwtService.generateToken(studentLoginDTO);
    }
}
