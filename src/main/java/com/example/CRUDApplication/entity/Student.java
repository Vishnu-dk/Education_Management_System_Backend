package com.example.CRUDApplication.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id", columnDefinition = "UUID")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

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


}
