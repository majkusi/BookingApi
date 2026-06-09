package com.majkusi.booking_api.application.dto;

import com.majkusi.booking_api.domain.MemberStatus;

import java.time.LocalDate;

public record MemberResponse( Long id,
                              String name,
                              String email,
                              LocalDate registerDate,
                              MemberStatus status ) {
}
