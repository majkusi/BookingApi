package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookResponse;
import com.majkusi.booking_api.domain.entity.BookEntity;
import com.majkusi.booking_api.instastructure.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService( BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

    public BookResponse create( String title, int pages, String author, String publisher ) {
        BookEntity bookEntity = new BookEntity( title, pages, author, publisher );
        BookEntity saved = bookRepository.save( bookEntity );
        return toResponse( saved );
    }

    public Optional< BookResponse > getById( Long id ) {
        return bookRepository.findById( id ).map( this::toResponse );
    }

    public List< BookResponse > getAllAsResponse( ) {
        return bookRepository.findAll( ).stream( )
                .map( this::toResponse )
                .toList( );
    }

    private BookResponse toResponse( BookEntity bookEntity ) {
        return new BookResponse( bookEntity.getId( ), bookEntity.getTitle( ), bookEntity.getPages( ), bookEntity.getAuthor( ), bookEntity.getPublisher( ) );
    }

}
