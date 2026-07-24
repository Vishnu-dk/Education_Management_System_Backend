package com.example.CRUDApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentLoginDTO {

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(
            regexp = "^\\d{2}[A-Z]\\d{3}$",
            message = "Admission number must match the strict format: YY[Letter]NNN (e.g., 22B030)"
    )
    private String admissionNo;

    @NotBlank
    @Email
    @Size(max = 225)
    private String email;

    @NotBlank
    @Size(min = 6, max = 225)
    private String password;

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getEmail() {
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
}
