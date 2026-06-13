package com.majkusi.booking_api.instastructure.security;

import com.majkusi.booking_api.domain.entity.MemberEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MemberDetails implements UserDetails {
    private final MemberEntity member;

    public MemberDetails( MemberEntity member ) {
        this.member = member;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities( ) {
        return List.of( );
    }

    @Override
    public String getPassword( ) {
        return member.getPassword( );
    }

    @Override
    public String getUsername( ) {
        return member.getEmail( );
    }

    @Override
    public boolean isAccountNonExpired( ) {
        return UserDetails.super.isAccountNonExpired( );
    }

    @Override
    public boolean isAccountNonLocked( ) {
        return UserDetails.super.isAccountNonLocked( );
    }

    @Override
    public boolean isCredentialsNonExpired( ) {
        return UserDetails.super.isCredentialsNonExpired( );
    }

    @Override
    public boolean isEnabled( ) {
        return UserDetails.super.isEnabled( );
    }
}
