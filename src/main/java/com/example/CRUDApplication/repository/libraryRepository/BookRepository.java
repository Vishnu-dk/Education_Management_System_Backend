package com.example.CRUDApplication.repository.libraryRepository;

import com.example.CRUDApplication.dto.libraryDTO.BookCreateDTO;
import com.example.CRUDApplication.dto.libraryDTO.BookUpdateDTO;
import com.example.CRUDApplication.entity.libraryEntity.Book;
import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import com.example.CRUDApplication.mapper.libraryMapper.MapRecordToBook;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class BookRepository {

    private final DSLContext dsl;

    public BookRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public boolean createBook(BookCreateDTO bookCreateDTO){
        UUID bookId=UUID.randomUUID();
        int created= dsl.insertInto(DSL.table("BOOK"))
                .set(DSL.field("book_id"),bookId)
                .set(DSL.field("book_title"),normalize(bookCreateDTO.getTitle()))
                .set(DSL.field("author_name"),normalize(bookCreateDTO.getAuthor()))
                .set(DSL.field("publisher"),normalize(bookCreateDTO.getPublisher()))
                .set(DSL.field("book_category"),bookCreateDTO.getCategory().name())
                .set(DSL.field("publication_year"),bookCreateDTO.getPublicationYear())
                .set(DSL.field("total_copies"),bookCreateDTO.getTotalCopies())
                .set(DSL.field("available_copies"),bookCreateDTO.getTotalCopies())
                .set(DSL.field("active"),true)
                .execute();
        return created>0;
    }
    public Record findById(UUID id){

        return dsl.select()
                .from("BOOK")
                .where(DSL.field("book_id").eq(id))
                .fetchOne();
    }
    public List<Record> findAll(String title, BookCategory category, String sortBy, String sortDirection){

        var query= dsl.select()
                .from("BOOK")
                .where(DSL.field("active").eq(true));


        if (title != null && !title.isBlank()) {
            query = query.and(
                    DSL.lower(DSL.field("book_title", String.class)).contains(title.toLowerCase())
            );
        }

        if (category != null) {
            query = query.and(DSL.field("book_category").eq(category.name()));
        }

        OrderField<?> orderField = null;
        if ("title".equalsIgnoreCase(sortBy)) {
            orderField = "desc".equalsIgnoreCase(sortDirection)
                    ? DSL.field("book_title").desc()
                    : DSL.field("book_title").asc();
        } else if ("year".equalsIgnoreCase(sortBy)) {
            orderField = "desc".equalsIgnoreCase(sortDirection)
                    ? DSL.field("publication_year").desc()
                    : DSL.field("publication_year").asc();
        }


        return orderField != null ? query.orderBy(orderField).fetch() : query.fetch();
    }

    public List<Record> findAllForAdmin(String title, BookCategory category, String sortBy, String sortDirection,Boolean active) {
        var query = dsl.select().from("BOOK");
        var condition = DSL.noCondition();

        if (title != null && !title.isBlank()) {
            condition = condition.and(
                    DSL.lower(DSL.field("book_title", String.class)).contains(title.toLowerCase())
            );
        }

        if (category != null) {
            condition = condition.and(DSL.field("book_category").eq(category.name()));
        }
        if(active!=null){
            if(active==true){
                condition=condition.and(DSL.field("active").isTrue());
            } else if (!active) {
                condition=condition.and(DSL.field("active").isFalse());
            }
        }



        OrderField<?> orderField = null;
        if ("title".equalsIgnoreCase(sortBy)) {
            orderField = "desc".equalsIgnoreCase(sortDirection)
                    ? DSL.field("book_title").desc()
                    : DSL.field("book_title").asc();
        } else if ("year".equalsIgnoreCase(sortBy)) {
            orderField = "desc".equalsIgnoreCase(sortDirection)
                    ? DSL.field("publication_year").desc()
                    : DSL.field("publication_year").asc();
        }

        var finalQuery = query.where(condition);
        return orderField != null ? finalQuery.orderBy(orderField).fetch() : finalQuery.fetch();
    }



    public boolean updateBook(UUID id,BookUpdateDTO dto){

        Record record=dsl.select()
                .from("BOOK")
                .where(DSL.field("book_id").eq(id))
                .fetchOne();
        Book book= MapRecordToBook.recordToBookMap(record);

        int updated =
                dsl.update(DSL.table("BOOK"))
                        .set(DSL.field("book_title"),dto.getTitle() != null ?dto.getTitle().trim(): book.getTitle())
                        .set(DSL.field("author_name"),dto.getAuthor() != null ?dto.getAuthor().trim(): book.getAuthor())
                        .set(DSL.field("publisher"),dto.getPublisher() != null ?dto.getPublisher().trim(): book.getPublisher())
                        .set(DSL.field("publication_year"),dto.getPublicationYear()!=null?dto.getPublicationYear():book.getPublicationYear())
                        .set(DSL.field("book_category"),dto.getCategory() != null ?dto.getCategory().name(): book.getCategory().name())
                        .set(DSL.field("total_copies"),dto.getTotalCopies()!=null?dto.getTotalCopies():book.getTotalCopies())
                        .set(DSL.field("available_copies"),dto.getAvailableCopies()!=null?dto.getAvailableCopies():book.getAvailableCopies())
                        .where(DSL.field("book_id").eq(id))
                        .execute();

        return updated > 0;
    }
    public boolean deactivateBook(UUID id){

        int updated =
                dsl.update(DSL.table("BOOK"))
                        .set(DSL.field("active"),false)
                        .where( DSL.field("book_id").eq(id))
                        .execute();
        return updated > 0;
    }
    public boolean activateBook(UUID id){

        int updated =
                dsl.update(DSL.table("BOOK"))
                        .set(DSL.field("active"),true)
                        .where(DSL.field("book_id").eq(id))
                        .execute();

        return updated > 0;
    }

    public boolean existsBook(String title,String author){

        return dsl.fetchExists(
                dsl.selectOne()
                        .from("BOOK")
                        .where(DSL.lower(DSL.field("book_title",String.class)).eq(title.trim().toLowerCase()))
                        .and(DSL.lower(DSL.field("author_name",String.class)).eq(author.trim().toLowerCase()))
        );
    }
    private String normalize(String value){
        if(value == null){
            return null;
        }
        return value.trim().toLowerCase();
    }

    public Record findActiveBookById(UUID bookId){

        return dsl.select()
                .from("BOOK")
                .where(DSL.field("book_id").eq(bookId))
                .and(DSL.field("active").eq(true))
                .fetchOne();
    }


    public void reduceCopies(UUID bookId){


        Book book=MapRecordToBook.recordToBookMap(findById(bookId));

        dsl.update(DSL.table("BOOK"))
                .set(DSL.field("available_copies"),book.getAvailableCopies()-1)
                .where(DSL.field("book_id").eq(bookId))
                .execute();
    }

    public void increaseCopies(UUID bookId){


        Book book=MapRecordToBook.recordToBookMap(findById(bookId));

        dsl.update(DSL.table("BOOK"))
                .set(DSL.field("available_copies"),book.getAvailableCopies()+1)
                .where(DSL.field("book_id").eq(bookId))
                .execute();
    }

}
