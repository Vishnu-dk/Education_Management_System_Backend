package com.example.CRUDApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentActionDTO {

    @NotBlank
    @Size(min = 1, max = 7)
    private String admissionNo;

    @NotBlank
    @Email
    @Size(max = 225)
    private String email;

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
}
