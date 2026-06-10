package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookCopyResponse;
import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import com.majkusi.booking_api.instastructure.BookCopyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {
    private final BookCopyRepository bookCopyRepository;

    public BookCopyService( BookCopyRepository bookCopyRepository ) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public BookCopyResponse create( Long bookId ) {
        BookCopyEntity bookCopy = new BookCopyEntity( bookId, BookStatus.AVAILABLE );
        BookCopyEntity saved = bookCopyRepository.save( bookCopy );
        return toResponse( saved );
    }

    public List< BookCopyResponse > getBookById( Long id ) {
        return bookCopyRepository.findByBookId( id ).stream( ).map( this::toResponse ).toList( );
    }

    public Optional< BookCopyResponse > getById( Long id ) {
        return bookCopyRepository.findById( id ).map( this::toResponse );
    }

    private BookCopyResponse toResponse( BookCopyEntity bookCopy ) {
        return new BookCopyResponse( bookCopy.getId( ), bookCopy.getBookId( ), bookCopy.getStatus( ) );
    }

}
