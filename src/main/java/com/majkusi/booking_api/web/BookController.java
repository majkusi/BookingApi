package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.BookService;
import com.majkusi.booking_api.application.dto.BookResponse;
import com.majkusi.booking_api.web.dto.CreateBookRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping( "/books" )
public class BookController {

    private final BookService bookService;

    public BookController( BookService bookService ) {
        this.bookService = bookService;
    }

    @GetMapping
    public List< BookResponse > getAll( ) {
        return bookService.getAllAsResponse( );
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public BookResponse createBook( @Valid @RequestBody CreateBookRequest request ) {
        return bookService.create( request.title( ), request.pages( ), request.author( ), request.publisher( ) );
    }

    @GetMapping( "/{id}" )
    public BookResponse getBookById( @PathVariable Long id ) {
        return bookService.getById( id ).orElseThrow( ( ) -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
    }
}
