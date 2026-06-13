package com.majkusi.booking_api.instastructure.security;

import com.majkusi.booking_api.domain.entity.MemberEntity;
import com.majkusi.booking_api.instastructure.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberDetailsService( MemberRepository memberRepository ) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        MemberEntity member = memberRepository.findByEmail( email ).orElseThrow( ( ) -> new UsernameNotFoundException( "Email " + email + " not found" ) );
        return new MemberDetails( member );
    }
}
