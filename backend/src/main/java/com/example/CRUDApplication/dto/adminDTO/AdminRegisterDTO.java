package com.example.CRUDApplication.dto.adminDTO;

import com.example.CRUDApplication.entity.adminEntity.AdminRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AdminRegisterDTO {

    @NotBlank
    @Email
    @Size(max = 225)
    private String email;

    @NotBlank
    @Size(min = 6, max = 225)
    private String password;

    @NotNull
    private AdminRole role;

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }
}