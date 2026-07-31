package com.example.CRUDApplication.repository.studentRepository;

import com.example.CRUDApplication.dto.studentDTO.StudentLoginDTO;
import com.example.CRUDApplication.dto.studentDTO.StudentRegisterDTO;
import com.example.CRUDApplication.entity.studentEntity.Student;
import com.example.CRUDApplication.mapper.studentMapper.MapRecordToStudent;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StudentAuthRepository {

    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public StudentAuthRepository(
            DSLContext dsl,
            PasswordEncoder passwordEncoder) {

        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email){

        return dsl.fetchExists(
                dsl.selectOne()
                        .from("STUDENT")
                        .where(DSL.field("email").eq(email))
        );
    }

    public void registerStudent(StudentRegisterDTO dto){

        String encodedPassword =
                passwordEncoder.encode(dto.getPassword());

        dsl.insertInto(DSL.table("STUDENT"))
                .set(DSL.field("student_id"), UUID.randomUUID())
                .set(DSL.field("email"), dto.getEmail())
                .set(DSL.field("password"), encodedPassword)
                .set(DSL.field("active"), true)
                .execute();
    }

    public boolean loginStudent(StudentLoginDTO dto){

        Record student = dsl.select()
                .from("STUDENT")
                .where(DSL.field("email").eq(dto.getEmail()))
                .fetchOne();

        if(student == null){
            return false;
        }

        String dbPassword =
                student.get("password", String.class);

        return passwordEncoder.matches(
                dto.getPassword(),
                dbPassword
        );
    }
    public Student findByEmail(String email){

        Record record = dsl.select()
                .from("STUDENT")
                .where(DSL.field("email").eq(email))
                .fetchOne();

        if(record == null){
            return null;
        }
        return MapRecordToStudent.recordToStudentMap(record);
    }
}