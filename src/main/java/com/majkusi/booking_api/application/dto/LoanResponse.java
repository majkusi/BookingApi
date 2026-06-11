package com.majkusi.booking_api.application.dto;

import com.majkusi.booking_api.domain.entity.BookCopyEntity;
import com.majkusi.booking_api.domain.entity.MemberEntity;

import java.time.LocalDate;
import java.util.Optional;

public record LoanResponse( Long id, BookCopyEntity bookCopy, MemberEntity member, LocalDate startDate,
                            LocalDate dueDate,
                            Optional< LocalDate > returnDate ) {
}
