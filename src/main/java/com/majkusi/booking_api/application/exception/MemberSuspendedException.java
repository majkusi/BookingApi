package com.majkusi.booking_api.application.exception;

public class MemberSuspendedException extends RuntimeException {
    public MemberSuspendedException( String message ) {
        super( message );
    }
}
