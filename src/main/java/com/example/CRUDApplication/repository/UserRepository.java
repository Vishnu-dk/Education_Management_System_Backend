package com.example.CRUDApplication.repository;

import com.example.CRUDApplication.dto.UserRegisterDTO;
import com.example.CRUDApplication.entity.Role;
import com.example.CRUDApplication.entity.User;
import jakarta.validation.constraints.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.UUID;



@Repository
public class UserRepository {

    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public UserRepository(
            DSLContext dsl,
            PasswordEncoder passwordEncoder) {

        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email) {

        return dsl.fetchExists(
                dsl.selectOne()
                        .from("USERS")
                        .where(DSL.field("email").eq(email))
        );
    }

    public UUID createUser(UserRegisterDTO dto) {

        String encodedPassword =
                passwordEncoder.encode(dto.getPassword());

        Record record =
                dsl.insertInto(DSL.table("USERS"))
                        .set(DSL.field("email"), dto.getEmail())
                        .set(DSL.field("password"), encodedPassword)
                        .set(DSL.field("role"), dto.getRole().name())
                        .set(DSL.field("active"), true)
                        .returning(DSL.field("id"))
                        .fetchOne();

        return record.get("id", UUID.class);
    }

    public User findByEmail(String email){

        Record record = dsl.select()
                .from("users")
                .where(DSL.field("email").eq(email))
                .fetchOne();

        if(record == null){
            return null;
        }

        User user = new User();

        user.setId(record.get("user_id", UUID.class));
        user.setEmail(record.get("email", String.class));
        user.setPassword(record.get("password", String.class));
        user.setRole(
                Role.valueOf(record.get("role", String.class))
        );

        return user;
    }
}
