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
    Long bookCopyId;
    Long memberId;
    LocalDate startDate;
    LocalDate dueDate;
    @Column( nullable = true )
    LocalDate returnDate;

    public LoanEntity( Long bookCopyId, Long memberId, LocalDate startDate, LocalDate dueDate, @Nullable LocalDate returnDate ) {
        this.bookCopyId = bookCopyId;
        this.memberId = memberId;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    protected LoanEntity( ) {
    }

    public Long getId( ) {
        return id;
    }

    public Long getBookCopyId( ) {
        return bookCopyId;
    }

    public Long getMemberId( ) {
        return memberId;
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
