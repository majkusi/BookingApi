package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.BookResponse;
import com.majkusi.booking_api.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final Map< Long, Book > books = new ConcurrentHashMap<>( );
    private final AtomicLong idGenerator = new AtomicLong( );

    public BookService( ) {
        add( new Book( null, "Atomic Habits", 326, "James Clear", "GALAKTYKA" ) );
        add( new Book( null, "Courage", 452, "Ryan Holiday", "Test" ) );
        add( new Book( null, "Meditations", 120, "Marcus Aurelius", "Test" ) );
    }

    public BookResponse create( String title, int pages, String author, String publisher ) {
        Book book = new Book( null, title, pages, author, publisher );
        Book saved = add( book );
        return toResponse( saved );
    }

    public Optional< BookResponse > getById( Long id ) {
        return Optional.ofNullable( books.get( id ) ).map( this::toResponse );
    }

    public List< BookResponse > getAllAsResponse( ) {
        return getAll( ).stream( )
                .map( this::toResponse )
                .toList( );
    }

    private BookResponse toResponse( Book book ) {
        return new BookResponse( book.id( ), book.title( ), book.pages( ), book.author( ), book.publisher( ) );
    }

    private List< Book > getAll( ) {
        return List.copyOf( books.values( ) );
    }

    private Book add( Book book ) {
        Long id = idGenerator.getAndIncrement( );
        Book bookWithId = new Book( id, book.title( ), book.pages( ), book.author( ), book.publisher( ) );
        books.put( id, bookWithId );
        return bookWithId;
    }
}
