package com.majkusi.booking_api.domain.entity;

import jakarta.persistence.*;

@Entity
@Table( name = "books" )
public class BookEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    private int pages;
    private String author;
    private String publisher;

    protected BookEntity( ) {
    }

    public BookEntity( String title, int pages, String author, String publisher ) {
        this.title = title;
        this.pages = pages;
        this.author = author;
        this.publisher = publisher;
    }

    public Long getId( ) {
        return id;
    }

    public String getTitle( ) {
        return title;
    }

    public int getPages( ) {
        return pages;
    }

    public String getAuthor( ) {
        return author;
    }

    public String getPublisher( ) {
        return publisher;
    }
}
