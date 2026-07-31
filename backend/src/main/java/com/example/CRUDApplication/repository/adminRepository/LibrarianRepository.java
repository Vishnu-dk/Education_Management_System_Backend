package com.example.CRUDApplication.repository.adminRepository;

import com.example.CRUDApplication.dto.adminDTO.LibrarianUpdateDTO;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class LibrarianRepository {

    private final DSLContext dsl;

    public LibrarianRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void createEmptyLibrarian(UUID userId) {
        dsl.insertInto(DSL.table("LIBRARIAN"))
                .set(DSL.field("librarian_id"),userId)
                .execute();
    }

    public Record findById(UUID id){

        return dsl.select()
                .from("LIBRARIAN")
                .where(DSL.field("librarian_id").eq(id))
                .fetchOne();
    }
    public boolean updateLibrarian(UUID id,LibrarianUpdateDTO dto){

        int updated = dsl.update(DSL.table("LIBRARIAN"))
                .set(DSL.field("librarian_name"),dto.getName())
                .set(DSL.field("phone_no"),dto.getPhoneNo())
                .set(DSL.field("date_of_join"),dto.getDateOfJoin())
                .where(DSL.field("librarian_id").eq(id))
                .execute();

        return updated > 0;
    }

    public boolean deleteLibrarian(UUID id) {
        int deleted=dsl.deleteFrom(DSL.table("LIBRARIAN"))
                .where(DSL.field("librarian_id").eq(id))
                .execute();
        dsl.deleteFrom(DSL.table("ADMIN"))
                .where(DSL.field("admin_id").eq(id))
                .execute();

        return deleted>0;
    }
}
