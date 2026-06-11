package com.majkusi.booking_api.application.dto;

import com.majkusi.booking_api.domain.BookStatus;
import com.majkusi.booking_api.domain.entity.BookEntity;

public record BookCopyResponse( Long id, BookEntity book, BookStatus status ) {
}
