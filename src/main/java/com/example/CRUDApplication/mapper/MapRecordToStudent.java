package com.example.CRUDApplication.mapper;

import com.example.CRUDApplication.entity.Courses;
import com.example.CRUDApplication.entity.Student;
import org.jooq.Record;

import java.util.UUID;

public class MapRecordToStudent {

    public static Student recordToStudentMap(Record record) {
        if (record == null) {
            return null;
        }

        Student student = new Student();

        student.setId(record.get("student_id", UUID.class));
        student.setName(record.get("student_name", String.class));
        student.setAdmissionNo(record.get("student_admission_no", String.class));
        student.setRollNo(record.get("student_rollno", String.class));
        student.setAge(record.get("student_age", Integer.class));
        String courseStr = record.get("course", String.class);
        if (courseStr != null) {
            student.setCourse(Courses.valueOf(courseStr));
        }


        return student;
    }
}
