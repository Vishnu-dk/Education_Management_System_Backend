package com.example.CRUDApplication.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue // Tells JPA/Hibernate that the database generates this value natively
    @Column(name = "student_id", columnDefinition = "UUID") // Maps explicit Postgres UUID column type
    private UUID id;

    @Column(name = "student_name", nullable = false)
    private String name;

    @Column(name = "student_admission_no", nullable = false, unique = true)
    private String admissionNo;

    @Column(name = "student_rollno", nullable = false, unique = true)
    private String rollNo;

    @Column(name = "student_age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "course")
    private Courses course;

    @Column(name = "student_email", nullable = false, unique = true)
    private String email;

    @Column(name = "student_password", nullable = false)
    private String password;


    public Student() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
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
