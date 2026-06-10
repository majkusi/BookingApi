package com.majkusi.booking_api.application.exception;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException( String message ) {
        super( message );
    }
}
