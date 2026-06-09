package com.majkusi.booking_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateBookRequest(
        @NotBlank( message = "Name must not be empty" )
        String title,
        @Positive( message = "Pages must be greater then 0" )
        int pages,
        @NotBlank( message = "Author must not be empty" )
        String author,
        @NotBlank( message = "Published must not be empty" )
        String publisher

) {
}
