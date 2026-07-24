package com.example.CRUDApplication.repository;

import com.example.CRUDApplication.dto.StudentLoginDTO;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository
public class AuthenticationRepository {


    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationRepository(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean studentLogin(StudentLoginDTO studentLoginDTO){

        Record studentExists = dsl.select()
                .from(DSL.table("USERS"))
                .where(DSL.field("student_email").eq(studentLoginDTO.getEmail()))
                .fetchOne();



        if(studentExists==null){
            return false;
        }
        String databaseHashedPassword=studentExists.get("student_password",String.class);
        return passwordEncoder.matches(studentLoginDTO.getPassword(),databaseHashedPassword);
    }
}
