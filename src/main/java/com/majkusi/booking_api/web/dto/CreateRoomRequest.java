package com.majkusi.booking_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateRoomRequest(
        @NotBlank( message = "Name must not be empty" )
        String name,
        @Positive( message = "Capacity must be greater then 0" )
        int capacity

) {
}
