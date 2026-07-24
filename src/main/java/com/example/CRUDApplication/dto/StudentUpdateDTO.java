package com.example.CRUDApplication.dto;

import com.example.CRUDApplication.entity.Courses;
import jakarta.validation.constraints.*;

public class StudentUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Min(1)
    @Max(70)
    private int age;

    @NotNull(message = "Please select a valid course option.")
    private Courses course;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
