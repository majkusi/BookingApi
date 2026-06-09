package com.majkusi.booking_api.application.dto;

import java.time.LocalDate;
import java.util.Optional;

public record LoanResponse( Long id, Long bookCopyId, Long memberId, LocalDate startDate, LocalDate dueDate,
                            Optional< LocalDate > returnDate ) {
}
