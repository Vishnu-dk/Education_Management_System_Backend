package com.example.CRUDApplication.dto.adminDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class LibrarianUpdateDTO {

    @NotBlank
    private String name;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain 10 digits"
    )
    private String phoneNo;

    private LocalDate dateOfJoin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDate getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(LocalDate dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }
}