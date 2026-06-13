package com.majkusi.booking_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest( @NotBlank @Email String email, @NotBlank String name, @NotBlank String password ) {
}
