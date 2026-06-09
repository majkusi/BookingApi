package com.majkusi.booking_api.domain;

import java.time.LocalDate;

public record Member( Long id,
                      String name,
                      String email,
                      LocalDate registerDate,
                      MemberStatus status ) {
}
