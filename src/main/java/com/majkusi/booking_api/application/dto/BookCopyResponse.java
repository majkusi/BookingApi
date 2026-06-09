package com.majkusi.booking_api.application.dto;

import com.majkusi.booking_api.domain.BookStatus;

public record BookCopyResponse( Long id, Long bookId, BookStatus status ) {
}
