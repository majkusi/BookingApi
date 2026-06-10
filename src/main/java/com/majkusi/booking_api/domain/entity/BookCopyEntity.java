package com.majkusi.booking_api.domain.entity;

import com.majkusi.booking_api.domain.BookStatus;
import jakarta.persistence.*;

@Entity
@Table( name = "book_copies" )
public class BookCopyEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;
    Long bookId;
    @Enumerated( EnumType.STRING )
    BookStatus status;

    public BookCopyEntity( Long bookId, BookStatus status ) {
        this.bookId = bookId;
        this.status = status;
    }

    protected BookCopyEntity( ) {
    }

    public Long getId( ) {
        return id;
    }

    public Long getBookId( ) {
        return bookId;
    }

    public BookStatus getStatus( ) {
        return status;
    }
}
