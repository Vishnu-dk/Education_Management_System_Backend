package com.example.CRUDApplication.entity.studentEntity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column(name = "student_id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_admission_no", unique = true)
    private String admissionNo;

    @Column(name = "student_rollno", unique = true)
    private String rollNo;

    @Column(name = "student_age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "course")
    private Courses course;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }


}
