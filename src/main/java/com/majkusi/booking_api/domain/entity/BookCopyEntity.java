package com.majkusi.booking_api.domain.entity;

import com.majkusi.booking_api.domain.BookStatus;
import jakarta.persistence.*;

@Entity
@Table( name = "book_copies" )
public class BookCopyEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;
    @ManyToOne
    @JoinColumn( name = "book_id", referencedColumnName = "id" )
    BookEntity book;
    @Enumerated( EnumType.STRING )
    BookStatus status;

    public BookCopyEntity( BookEntity book, BookStatus status ) {
        this.book = book;
        this.status = status;
    }

    protected BookCopyEntity( ) {
    }

    public Long getId( ) {
        return id;
    }

    public BookEntity getBook( ) {
        return book;
    }

    public BookStatus getStatus( ) {
        return status;
    }

    public void setStatus( BookStatus status ) {
        this.status = status;
    }
}
