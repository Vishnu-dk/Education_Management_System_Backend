package com.example.CRUDApplication.repository.studentRepository;

import com.example.CRUDApplication.dto.studentDTO.StudentUpdateDTO;
import jakarta.validation.constraints.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository {

    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public StudentRepository(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }



    public boolean update(UUID id, @NotNull StudentUpdateDTO student) {
        boolean recordExists = false;

        if (id != null) {
            recordExists = dsl.fetchExists(
                    dsl.selectOne()
                            .from(DSL.table("STUDENT"))
                            .where(DSL.field("student_id").eq(id))
            );
        }

        if (recordExists) {
            dsl.update(DSL.table("STUDENT"))
                    .set(DSL.field("student_name"), student.getName())
                    .set(DSL.field("student_age"), student.getAge())
                    .set(DSL.field("student_admission_no"), student.getAdmissionNo())
                    .set(DSL.field("student_rollno"), student.getRollNo())
                    .set(DSL.field("course"), student.getCourse()!=null?student.getCourse().name():null)
                    .where(DSL.field("student_id").eq(id))
                    .execute();

            return true;
        }
        return false;
    }

    public Boolean deleteById(@NotNull UUID id) {
        boolean dataExists = false;

        if (id != null) {
            dataExists = dsl.fetchExists(
                    dsl.selectOne()
                            .from(DSL.table("STUDENT"))
                            .where(DSL.field("student_id").eq(id))

            );
        }
        if (dataExists) {
            dsl.deleteFrom(DSL.table("STUDENT"))
                    .where(DSL.field("student_id").eq(id))
                    .execute();
            dsl.deleteFrom(DSL.table("USERS"))
                    .where(DSL.field("user_id").eq(id))
                    .execute();
            return true;
        }
        return false;
    }

    public Record findByIdAndDeletedIsFalse(@NotNull UUID id) {

        return dsl.select()
                .from(DSL.table("STUDENT"))
                .where(DSL.field("student_id").eq(id))
                .fetchOne();
    }

    public List<Record> findByDeletedIsFalse() {
        return dsl.select()
                .from(DSL.table("STUDENT"))
                .fetch();
    }

}
