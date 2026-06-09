package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.LoanService;
import com.majkusi.booking_api.application.dto.LoanResponse;
import com.majkusi.booking_api.web.dto.CreateLoanRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/loans" )
public class LoanController {

    private final LoanService loanService;

    public LoanController( LoanService loanService ) {
        this.loanService = loanService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public LoanResponse createLoan( @Valid @RequestBody CreateLoanRequest request ) {
        return loanService.create( request.bookId( ), request.memberId( ) );
    }

    @PostMapping( "/{id}/return" )
    @ResponseStatus( HttpStatus.ACCEPTED )
    public LoanResponse returnLoan( @PathVariable Long id ) {
        return loanService.returnLoan( id );
    }

}
