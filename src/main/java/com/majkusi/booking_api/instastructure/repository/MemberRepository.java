package com.majkusi.booking_api.instastructure.repository;

import com.majkusi.booking_api.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository< MemberEntity, Long > {

    Optional< MemberEntity > findByEmail( String email );
}
