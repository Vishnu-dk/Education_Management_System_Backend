package com.example.CRUDApplication.dto.adminDTO;

import java.time.LocalDate;

public class LibrarianResponseDTO {

    private String name;
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