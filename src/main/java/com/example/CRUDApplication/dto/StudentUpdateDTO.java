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

    @NotBlank
    @Size(min = 6, max = 6)
    @Pattern(
            regexp = "^\\d{2}[A-Z]\\d{3}$",
            message = "Admission number must match the strict format: YY[Letter]NNN (e.g., 22B030)"
    )
    private String admissionNo;

    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(
            regexp = "^[A-Z]{3}\\d{2}[A-Z]{2}\\d{3}$",
            message = "Roll number must match the format: [CLG CODE]YY[DEPT CODE]NNN (e.g., TCR22EC066)"
    )
    private String rollNo;


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
}
