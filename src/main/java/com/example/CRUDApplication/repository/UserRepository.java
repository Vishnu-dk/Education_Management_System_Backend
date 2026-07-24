package com.example.CRUDApplication.repository;

import com.example.CRUDApplication.entity.Role;
import com.example.CRUDApplication.entity.User;
import jakarta.validation.constraints.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.UUID;

public class UserRepository {

    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
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
