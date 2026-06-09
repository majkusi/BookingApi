package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookCopyResponse;
import com.majkusi.booking_api.domain.BookCopy;
import com.majkusi.booking_api.domain.BookStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookCopyService {
    private final Map< Long, BookCopy > bookCopies = new ConcurrentHashMap<>( );
    private final AtomicLong idGenerator = new AtomicLong( );

    public BookCopyService( ) {
    }

    public BookCopyResponse create( Long bookId ) {
        BookCopy bookCopy = new BookCopy( null, bookId, BookStatus.AVAILABLE );
        BookCopy saved = add( bookCopy );
        return toResponse( saved );
    }

    public List< BookCopyResponse > getBookById( Long id ) {
        return bookCopies.values( ).stream( ).filter( b -> Objects.equals( b.bookId( ), id ) ).map( this::toResponse ).toList( );
    }

    private BookCopyResponse toResponse( BookCopy bookCopy ) {
        return new BookCopyResponse( bookCopy.id( ), bookCopy.bookId( ), bookCopy.status( ) );
    }

    private BookCopy add( BookCopy bookCopy ) {
        Long id = idGenerator.getAndIncrement( );
        BookCopy bookWithId = new BookCopy( id, bookCopy.bookId( ), bookCopy.status( ) );
        bookCopies.put( id, bookWithId );
        return bookWithId;
    }

}
