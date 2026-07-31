package com.example.CRUDApplication.entity.adminEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "LIBRARIAN")
public class Librarian {

    @Id
    @Column(name = "librarian_id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "librarian_name")
    private String name;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "date_of_join")
    private LocalDate dateOfJoin;

    public Librarian() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
