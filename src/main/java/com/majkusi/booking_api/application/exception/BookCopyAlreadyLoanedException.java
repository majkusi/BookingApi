package com.majkusi.booking_api.application.exception;

public class BookCopyAlreadyLoanedException extends RuntimeException {
    public BookCopyAlreadyLoanedException( String message ) {
        super( message );
    }
}
