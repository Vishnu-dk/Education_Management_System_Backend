package com.example.CRUDApplication.service;

import com.example.CRUDApplication.entity.adminEntity.Admin;
import com.example.CRUDApplication.entity.studentEntity.Student;
import com.example.CRUDApplication.repository.adminRepository.AdminAuthRepository;
import com.example.CRUDApplication.repository.studentRepository.StudentAuthRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomAuthService
        implements UserDetailsService {

    private final StudentAuthRepository studentAuthRepository;
    private final AdminAuthRepository adminAuthRepository;

    public CustomAuthService(StudentAuthRepository studentAuthRepository, AdminAuthRepository adminAuthRepository) {
        this.studentAuthRepository = studentAuthRepository;


        this.adminAuthRepository = adminAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        Student student =
                studentAuthRepository.findByEmail(email);

        if(student != null){
            return new User(
                    student.getEmail(),
                    student.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority(
                                    "ROLE_STUDENT"
                            )
                    )
            );
        }

        Admin admin =
                adminAuthRepository.findByEmail(email);

        if(admin != null){
            return new User(
                    admin.getEmail(),
                    admin.getPassword(),
                    List.of(
                            new SimpleGrantedAuthority(
                                    "ROLE_" +
                                            admin.getRole().name()
                            )
                    )
            );
        }

        throw new UsernameNotFoundException(
                "User not found"
        );
    }
}
