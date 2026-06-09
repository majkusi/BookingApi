package com.majkusi.booking_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateMemberRequest( @NotBlank String name, @NotBlank @Email String email ) {
}
