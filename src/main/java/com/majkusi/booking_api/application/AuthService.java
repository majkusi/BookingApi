package com.majkusi.booking_api.application;

import com.majkusi.booking_api.domain.MemberStatus;
import com.majkusi.booking_api.domain.entity.MemberEntity;
import com.majkusi.booking_api.instastructure.repository.MemberRepository;
import com.majkusi.booking_api.web.dto.RegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {
    private final BCryptPasswordEncoder encoder;
    private final MemberRepository memberRepository;

    public AuthService( BCryptPasswordEncoder encoder, MemberRepository memberRepository ) {
        this.encoder = encoder;
        this.memberRepository = memberRepository;
    }

    public void register( RegisterRequest request ) {
        MemberEntity member = new MemberEntity( request.name( ), request.email( ), encoder.encode( request.password( ) ), LocalDate.now( ), MemberStatus.ACTIVE );
        memberRepository.save( member );
    }

}
