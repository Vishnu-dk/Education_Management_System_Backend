package com.example.CRUDApplication.mapper.libraryMapper;

import com.example.CRUDApplication.entity.libraryEntity.Book;
import com.example.CRUDApplication.entity.libraryEntity.BookCategory;
import org.jooq.Record;

import java.util.UUID;

public class MapRecordToBook {

    public static Book recordToBookMap(Record record){

        if(record == null){
            return null;
        }
        Book book = new Book();
        book.setId(record.get("book_id", UUID.class));
        book.setTitle(record.get("book_title", String.class));
        book.setAuthor(record.get("author_name", String.class));
        book.setPublisher(record.get("publisher", String.class));
        book.setPublicationYear(record.get("publication_year", Integer.class));

        String category =record.get("book_category", String.class);

        if(category != null){
            book.setCategory(
                    BookCategory.valueOf(category)
            );
        }

        book.setTotalCopies(record.get("total_copies", Integer.class));
        book.setAvailableCopies(record.get("available_copies", Integer.class));
        Boolean active =record.get("active", Boolean.class);
        book.setActive(active != null && active);

        return book;
    }
}