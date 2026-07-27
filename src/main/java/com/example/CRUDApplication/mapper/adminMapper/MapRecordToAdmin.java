package com.example.CRUDApplication.mapper.adminMapper;

import com.example.CRUDApplication.entity.adminEntity.Admin;
import com.example.CRUDApplication.entity.adminEntity.AdminRole;
import org.jooq.Record;

import java.util.UUID;

public class MapRecordToAdmin {
    public static Admin mapRecordToAdmin(Record record){
        if(record == null){
            return null;
        }

        Admin admin = new Admin();

        admin.setId(record.get("admin_id", UUID.class));

        admin.setEmail(record.get("email", String.class));

        admin.setPassword(record.get("password", String.class));

        admin.setRole(
                AdminRole.valueOf(record.get("role", String.class)
                ));

        admin.setActive(
                Boolean.TRUE.equals(record.get("active", Boolean.class)
                ));

        return admin;
    }
}
