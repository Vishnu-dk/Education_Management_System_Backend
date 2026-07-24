package com.example.CRUDApplication.mapper;

import com.example.CRUDApplication.dto.StudentResponseDTO;
import com.example.CRUDApplication.entity.Student;

public class MapStudentToStudentResponseDTO {

    public static StudentResponseDTO StudentToStudentDTOMap (Student student) {
        if (student == null) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();


        studentResponseDTO.setAdmissionNo(student.getAdmissionNo());


        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setAge(student.getAge());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setRollNo(student.getRollNo());


        studentResponseDTO.setCourse(student.getCourse());

        return studentResponseDTO;
    }
}
