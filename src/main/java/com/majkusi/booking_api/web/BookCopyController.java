package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.BookCopyService;
import com.majkusi.booking_api.application.dto.BookCopyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/books" )
public class BookCopyController {

    private final BookCopyService bookCopyService;

    public BookCopyController( BookCopyService bookCopyService ) {
        this.bookCopyService = bookCopyService;
    }

    @PostMapping( "/{bookId}/copies" )
    @ResponseStatus( HttpStatus.CREATED )
    public Optional< BookCopyResponse > addBookCopy( @PathVariable Long bookId ) {
        bookCopyService.create( bookId );
        return bookCopyService.getById( bookId );
    }

    @GetMapping( "/{bookId}/copies" )
    public List< BookCopyResponse > getBooksById( @PathVariable Long bookId ) {
        return bookCopyService.getBookById( bookId );
    }
}
