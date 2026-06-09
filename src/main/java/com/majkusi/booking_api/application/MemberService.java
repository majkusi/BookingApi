package com.majkusi.booking_api.application;

import com.majkusi.booking_api.application.dto.MemberResponse;
import com.majkusi.booking_api.domain.Member;
import com.majkusi.booking_api.domain.MemberStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberService {
    private final Map< Long, Member > members = new ConcurrentHashMap<>( );
    private final AtomicLong idGenerator = new AtomicLong( );

    public MemberService( ) {

    }

    public MemberResponse create( String name, String email ) {
        Member member = new Member( null, name, email, LocalDate.now( ), MemberStatus.ACTIVE );
        Member saved = add( member );
        return toResponse( saved );
    }

    private Member add( Member member ) {
        Long id = idGenerator.getAndIncrement( );
        Member memberWithId = new Member( id, member.name( ), member.email( ), member.registerDate( ), member.status( ) );
        members.put( id, memberWithId );
        return memberWithId;
    }

    public Optional< MemberResponse > getById( Long id ) {
        return Optional.ofNullable( members.get( id ) ).map( this::toResponse );
    }

    public List< MemberResponse > getMembers( ) {
        List< MemberResponse > responseMembers = new ArrayList<>( );
        for ( Member member : members.values( ) )
            responseMembers.add( toResponse( member ) );
        return responseMembers;
    }


    private MemberResponse toResponse( Member member ) {
        return new MemberResponse( member.id( ), member.name( ), member.email( ), member.registerDate( ), member.status( ) );
    }


}
