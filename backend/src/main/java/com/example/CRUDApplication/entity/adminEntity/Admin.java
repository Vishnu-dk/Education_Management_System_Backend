package com.example.CRUDApplication.entity.adminEntity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ADMIN")
public class Admin {

    @Id
    @Column(name = "admin_id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "email",
            nullable = false,
            unique = true)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AdminRole role;

    @Column(name = "active")
    private boolean active;

    public Admin() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public AdminRole getRole() {
        return role;
    }

    public void setRole(AdminRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}