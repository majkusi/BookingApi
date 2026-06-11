package com.majkusi.booking_api.domain.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "loans" )
public class LoanEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    Long id;
    @ManyToOne
    @JoinColumn( name = "book_copy_id", referencedColumnName = "id" )
    BookCopyEntity bookCopy;
    @ManyToOne
    @JoinColumn( name = "member_id", referencedColumnName = "id" )
    MemberEntity member;
    LocalDate startDate;
    LocalDate dueDate;
    @Column( nullable = true )
    LocalDate returnDate;

    public LoanEntity( BookCopyEntity bookCopy, MemberEntity member, LocalDate startDate, LocalDate dueDate, @Nullable LocalDate returnDate ) {
        this.bookCopy = bookCopy;
        this.member = member;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    protected LoanEntity( ) {
    }

    public Long getId( ) {
        return id;
    }

    public BookCopyEntity getBookCopy( ) {
        return bookCopy;
    }

    public MemberEntity getMember( ) {
        return member;
    }

    public LocalDate getStartDate( ) {
        return startDate;
    }

    public LocalDate getDueDate( ) {
        return dueDate;
    }

    @Nullable
    public LocalDate getReturnDate( ) {
        return returnDate;
    }

    public void setReturnDate( @Nullable LocalDate returnDate ) {
        this.returnDate = returnDate;
    }
}
