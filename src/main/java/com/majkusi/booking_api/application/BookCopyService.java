package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookCopyResponse;
import com.majkusi.booking_api.application.exception.BookNotFoundException;
import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import com.majkusi.booking_api.domain.entity.BookEntity;
import com.majkusi.booking_api.instastructure.repository.BookCopyRepository;
import com.majkusi.booking_api.instastructure.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService( BookCopyRepository bookCopyRepository, BookRepository bookRepository ) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    public BookCopyResponse create( Long bookId ) {
        BookEntity book = bookRepository.findById( bookId ).orElseThrow( ( ) -> new BookNotFoundException( "Book with given Id not found" ) );
        BookCopyEntity bookCopy = new BookCopyEntity( book, BookStatus.AVAILABLE );
        BookCopyEntity saved = bookCopyRepository.save( bookCopy );
        return toResponse( saved );
    }

    public List< BookCopyResponse > getBookById( Long bookId ) {
        return bookCopyRepository.findByBook_Id( bookId ).stream( ).map( this::toResponse ).toList( );
    }

    public Optional< BookCopyResponse > getById( Long id ) {
        return bookCopyRepository.findById( id ).map( this::toResponse );
    }

    private BookCopyResponse toResponse( BookCopyEntity bookCopy ) {
        return new BookCopyResponse( bookCopy.getId( ), bookCopy.getBook( ), bookCopy.getStatus( ) );
    }

}
