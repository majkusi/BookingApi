package com.majkusi.booking_api.web.dto;

import jakarta.validation.constraints.NotNull;

public record CreateLoanRequest( @NotNull( message = "MemberId cannot be empty" ) Long memberId,
                                 @NotNull( message = "BookId cannot be empty" ) Long bookId ) {

}
