package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.MemberResponse;
import com.majkusi.booking_api.domain.MemberStatus;
import com.majkusi.booking_api.domain.entity.MemberEntity;
import com.majkusi.booking_api.instastructure.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService( MemberRepository memberRepository ) {
        this.memberRepository = memberRepository;

    }

    public MemberResponse create( String name, String email ) {
        MemberEntity member = new MemberEntity( name, email, LocalDate.now( ), MemberStatus.ACTIVE );
        MemberEntity saved = memberRepository.save( member );
        return toResponse( saved );
    }

    public Optional< MemberResponse > getById( Long id ) {
        return memberRepository.findById( id ).map( this::toResponse );
    }

    public List< MemberResponse > getMembers( ) {
        return memberRepository.findAll( ).stream( ).map( this::toResponse ).toList( );
    }

    private MemberResponse toResponse( MemberEntity member ) {
        return new MemberResponse( member.getId( ), member.getName( ), member.getEmail( ), member.getRegisterDate( ), member.getStatus( ) );
    }


}
