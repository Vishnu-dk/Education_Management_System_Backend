package com.example.CRUDApplication.repository.adminRepository;

import com.example.CRUDApplication.dto.adminDTO.AdminLoginDTO;
import com.example.CRUDApplication.dto.adminDTO.AdminRegisterDTO;
import com.example.CRUDApplication.entity.adminEntity.Admin;
import com.example.CRUDApplication.mapper.adminMapper.MapRecordToAdmin;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AdminAuthRepository {

    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthRepository(
            DSLContext dsl,
            PasswordEncoder passwordEncoder) {

        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email){

        return dsl.fetchExists(
                dsl.selectOne()
                        .from("ADMIN")
                        .where(DSL.field("email").eq(email))
        );
    }

    public UUID registerAdmin(AdminRegisterDTO dto){

        UUID adminId = UUID.randomUUID();

        String encodedPassword =
                passwordEncoder.encode(dto.getPassword());

        dsl.insertInto(DSL.table("ADMIN"))
                .set(DSL.field("admin_id"), adminId)
                .set(DSL.field("email"), dto.getEmail())
                .set(DSL.field("password"), encodedPassword)
                .set(DSL.field("role"), dto.getRole().name())
                .set(DSL.field("active"), true)
                .execute();

        return adminId;
    }

    public boolean loginAdmin(AdminLoginDTO dto){

        Record admin = dsl.select()
                .from("ADMIN")
                .where(DSL.field("email").eq(dto.getEmail()))
                .fetchOne();

        if(admin == null){
            return false;
        }

        String dbPassword =
                admin.get("password", String.class);

        return passwordEncoder.matches(
                dto.getPassword(),
                dbPassword
        );
    }

    public Admin findByEmail(String email){

        Record record = dsl.select()
                .from("ADMIN")
                .where(DSL.field("email").eq(email))
                .fetchOne();

        return MapRecordToAdmin.mapRecordToAdmin(record);
    }
}