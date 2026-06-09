package com.majkusi.booking_api.domain;

import java.time.LocalDate;
import java.util.Optional;

public record Loan( Long id, Long bookCopyId, Long memberId, LocalDate startDate, LocalDate dueDate,
                    Optional< LocalDate > returnDate ) {
}
