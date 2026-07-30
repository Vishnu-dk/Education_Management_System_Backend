package com.example.CRUDApplication.repository.libraryRepository;

import com.example.CRUDApplication.entity.libraryEntity.IssueStatus;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class BookIssueRepository {

    private final DSLContext dsl;

    public BookIssueRepository(DSLContext dsl) {
        this.dsl = dsl;
    }


    public void requestBook(UUID studentId, UUID bookId) {

        dsl.insertInto(DSL.table("BOOK_ISSUE"))
                .set(DSL.field("issue_id"), UUID.randomUUID())
                .set(DSL.field("student_id"), studentId)
                .set(DSL.field("book_id"), bookId)
                .set(DSL.field("request_date"), LocalDate.now())
                .set(DSL.field("fine_amount"), BigDecimal.ZERO)
                .set(DSL.field("fine_paid"), false)
                .set(DSL.field("active"),true)
                .set(DSL.field("status"),
                        IssueStatus.REQUESTED.name())
                .execute();
    }


    public Record findById(UUID issueId) {

        return dsl.select()
                .from("BOOK_ISSUE")
                .where( DSL.field("issue_id").eq(issueId))
                .fetchOne();
    }



    public Result<? extends  Record> findAllRequests(Boolean active, LocalDate approveDate, LocalDate dueDate, IssueStatus status,String sortDirection,String sortBy) {

        var query= dsl.select(
                        DSL.field("s.student_name").as("student_name"),
                        DSL.field("b.book_title").as("book_title"),
                        DSL.field("l.librarian_name").as("librarian_name"),
                        DSL.field("bi.request_date").as("request_date"),
                        DSL.field("bi.approved_date").as("approved_date"),
                        DSL.field("bi.due_date").as("due_date"),
                        DSL.field("bi.return_date").as("return_date"),
                        DSL.field("bi.fine_amount").as("fine_amount"),
                        DSL.field("bi.fine_paid").as("fine_paid"),
                        DSL.field("bi.status").as("status"),
                        DSL.field("bi.issue_id").as("issue_id")
                )
                .from(DSL.table("BOOK_ISSUE").as("bi"))
                .join(DSL.table("STUDENT").as("s"))
                .on(DSL.field("bi.student_id")
                        .eq(DSL.field("s.student_id")))
                .join(DSL.table("BOOK").as("b"))
                .on(DSL.field("bi.book_id")
                        .eq(DSL.field("b.book_id")))
                .leftJoin(DSL.table("LIBRARIAN").as("l"))
                .on(DSL.field("bi.librarian_id")
                        .eq(DSL.field("l.librarian_id")));

        var conditon=DSL.noCondition();

        if(active!=null){
            if(active){
                conditon=conditon.and(DSL.field("bi.active").isTrue());
            }
            conditon= conditon.and(DSL.field("bi.active").isFalse());
        }
        if(status!=null){
            conditon=conditon.and(DSL.field("status").eq(status.name()));
        }
        if(approveDate!=null){
            conditon=conditon.and(DSL.field("approved_date").eq(approveDate))
                    .and(DSL.field("approved_date").isNotNull());
        }
        if(dueDate!=null){
            conditon=conditon.and(DSL.field("due_date").eq(dueDate))
                    .and(DSL.field("due_date").isNotNull());
        }


        OrderField<?> orderField=null;
        if("approvedate".equalsIgnoreCase(sortBy)){
            orderField="desc".equalsIgnoreCase(sortDirection)
                    ?DSL.field("approved_date").isNotNull().desc()
                    :DSL.field("approved_date").asc();
        }

        if("due_date".equalsIgnoreCase(sortBy)){
            orderField="desc".equalsIgnoreCase(sortDirection)
                    ?DSL.field("due_date").isNotNull().desc()
                    :DSL.field("due_date").isNotNull().asc();
        }


        var orderResult=query
                        .where(DSL.field("approved_date")
                        .isNotNull())
                        .orderBy(orderField)
                        .fetch();


        return orderField!=null? orderResult:query.where(conditon).fetch();

    }


    public List<Record> findPendingRequests() {

        return dsl.select()
                .from("BOOK_ISSUE")
                .where(DSL.field("status").eq(IssueStatus.REQUESTED.name()))
                .fetch();
    }


    public boolean approveRequest(UUID issueId,UUID librarianId) {

        int updated =
                dsl.update(DSL.table("BOOK_ISSUE"))
                        .set(DSL.field("librarian_id"),librarianId)
                        .set(DSL.field("approved_date"),LocalDate.now())
                        .set(DSL.field("due_date"),LocalDate.now().plusDays(14))
                        .set(DSL.field("status"),IssueStatus.ISSUED.name())
                        .where(DSL.field("issue_id").eq(issueId))
                        .execute();

        return updated > 0;
    }


    public boolean rejectRequest(UUID issueId) {

        int updated =
                dsl.update(DSL.table("BOOK_ISSUE"))
                        .set(DSL.field("status"),IssueStatus.REJECTED.name())
                        .set(DSL.field("active"),false)
                        .where(DSL.field("issue_id").eq(issueId))
                        .execute();

        return updated > 0;
    }


    public boolean returnBook(UUID issueId) {

        int updated =
                dsl.update(DSL.table("BOOK_ISSUE"))
                        .set(DSL.field("return_date"),LocalDate.now())
                        .set(DSL.field("active"),false)
                        .set(DSL.field("status"),IssueStatus.RETURNED.name())
                        .where(DSL.field("issue_id").eq(issueId))
                        .execute();

        return updated > 0;
    }
    public boolean updateFine(
            UUID issueId,
            BigDecimal fineAmount) {

        int updated =
                dsl.update(DSL.table("BOOK_ISSUE"))
                        .set(DSL.field("fine_amount"),fineAmount)
                        .where(DSL.field("issue_id").eq(issueId))
                        .execute();

        return updated > 0;
    }
    public boolean markFinePaid(UUID issueId) {

        int updated =
                dsl.update(DSL.table("BOOK_ISSUE"))
                        .set(DSL.field("fine_paid"),true)
                        .where(DSL.field("issue_id").eq(issueId))
                        .execute();

        return updated > 0;
    }


    public Result<? extends  Record> getStudentBooks(UUID studentId,Boolean active, LocalDate approveDate, LocalDate dueDate, IssueStatus status,String sortDirection,String sortBy){

        var query= dsl.select(
                        DSL.field("s.student_name").as("student_name"),
                        DSL.field("b.book_title").as("book_title"),
                        DSL.field("l.librarian_name").as("librarian_name"),
                        DSL.field("bi.request_date").as("request_date"),
                        DSL.field("bi.approved_date").as("approved_date"),
                        DSL.field("bi.due_date").as("due_date"),
                        DSL.field("bi.return_date").as("return_date"),
                        DSL.field("bi.fine_amount").as("fine_amount"),
                        DSL.field("bi.fine_paid").as("fine_paid"),
                        DSL.field("bi.status").as("status"),
                        DSL.field("bi.issue_id").as("issue_id")

                )
                .from(DSL.table("BOOK_ISSUE").as("bi"))
                .join(DSL.table("STUDENT").as("s"))
                .on(DSL.field("bi.student_id")
                        .eq(DSL.field("s.student_id")))
                .join(DSL.table("BOOK").as("b"))
                .on(DSL.field("bi.book_id")
                        .eq(DSL.field("b.book_id")))
                .leftJoin(DSL.table("LIBRARIAN").as("l"))
                .on(DSL.field("bi.librarian_id")
                        .eq(DSL.field("l.librarian_id")))
                .where(
                        DSL.field("bi.student_id")
                                .eq(studentId)
                );

        var condition=DSL.noCondition();

        if(active!=null){
            if(active){
                condition=condition.and(DSL.field("bi.active").isTrue());
            }
            condition=condition.and(DSL.field("bi.active").isFalse());
        }

        if(approveDate!=null){
            condition=condition.and(DSL.field("approved_date").eq(approveDate));
        }
        if(dueDate!=null){
            condition=condition.and(DSL.field("due_date").eq(dueDate));
        }
        if(status!=null){
            condition=condition.and(DSL.field("status").eq(status.name()));
        }
        var finalQuery=query.and(condition);

        OrderField<?> orderField=null;

        if("approved_date".equalsIgnoreCase(sortBy)){
            orderField="desc".equalsIgnoreCase(sortDirection)
                    ?DSL.field("approved_date").isNotNull().desc()
                    :DSL.field("approved_date").asc();
        }

        if("due_date".equalsIgnoreCase(sortBy)){
            orderField="desc".equalsIgnoreCase(sortDirection)
                    ?DSL.field("due_date").isNotNull().desc()
                    :DSL.field("due_date").asc();
        }





        return orderField==null? finalQuery.fetch():finalQuery.orderBy(orderField).fetch();
    }

    public boolean studentAlreadyRequested(
            UUID studentId,
            UUID bookId){

        return dsl.fetchExists(
                dsl.selectOne()
                        .from("BOOK_ISSUE")
                        .where(DSL.field("student_id").eq(studentId))
                        .and(DSL.field("book_id").eq(bookId))
                        .and(DSL.field("active").eq(true))
        );
    }
}