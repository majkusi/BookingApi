package com.majkusi.booking_api.application.exception;

public class MemberHasOverdueException extends RuntimeException {
    public MemberHasOverdueException( String message ) {
        super( message );
    }
}
