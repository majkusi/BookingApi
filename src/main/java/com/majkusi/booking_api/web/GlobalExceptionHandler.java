package com.majkusi.booking_api.web;

import com.majkusi.booking_api.application.exception.*;
import com.majkusi.booking_api.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( BookCopyAlreadyLoanedException.class )
    public ResponseEntity< ErrorResponse > handleConflictBookCopyAlreadyLoanedException( BookCopyAlreadyLoanedException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.CONFLICT );
    }

    @ExceptionHandler( NoSuchElementException.class )
    public ResponseEntity< ErrorResponse > handleConflictNoSuchElementException( NoSuchElementException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( LoanLimitExceededException.class )
    public ResponseEntity< ErrorResponse > handleConflictLoanLimitExceededException( LoanLimitExceededException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.CONFLICT );
    }

    @ExceptionHandler( MemberHasOverdueException.class )
    public ResponseEntity< ErrorResponse > handleConflictMemberHasOverdueException( MemberHasOverdueException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.CONFLICT );
    }

    @ExceptionHandler( MemberSuspendedException.class )
    public ResponseEntity< ErrorResponse > handleConflictMemberSuspendedException( MemberSuspendedException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.CONFLICT );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity< ErrorResponse > handleConflictMethodArgumentNotValidException( MethodArgumentNotValidException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getBindingResult( ).getFieldErrors( ).get( 0 ).getDefaultMessage( ) ), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler( BookNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleConflictBookNotFoundException( BookNotFoundException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler( MemberNotFoundException.class )
    public ResponseEntity< ErrorResponse > handleConflictMemberNotFoundException( MemberNotFoundException ex ) {
        return new ResponseEntity<>( new ErrorResponse( ex.getMessage( ) ), HttpStatus.NOT_FOUND );
    }
}
